<template>
    <div class="Paint">
        <h1>{{ msg }}</h1>
    </div>
    <!-- the user interface code -->
    <div class="main">
        
        <!-- operations on board buttons -->
        <div>
            <button class="opt" @click="addMachine()" title="Add machine">Add machine</button>
            <button class="opt" @click="addQueue" title="Add queue">Add queue</button>
            <button class="opt" @click="connect" title="Connect">Connect</button>
            <button class="opt" @click="delet" title="delete">Delete</button>
            <button class="opt" @click="clearRequest()" title="clear screen">New Diagram</button>
            <button class="opt" title="previous">Previous diagram</button>
        </div>
        <div>
            <button class="smiul" @click="startSim()" title="Start">Start simulation</button>
        </div>
        <!-- drawing area -->
        <canvas
            id="myCanvas"
            width="1500"
            height="800"
            class="drawing-board"
            @click="dr"
        ></canvas>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "Paint",
    props: {
        msg: String
    },
    /* data used in the front-end project */
    data() {
        return {
            operation: null,
            mi:1,
            qi:1,
            
            diagram: {
                machines:[],
                queues:[],
                machinesInL:[],
                machinesOutL:[]
            },
            machine: {
                location: {
                    x: 0,
                    y:0
                }
            },
            queue: {
                location: {
                    x: 0,
                    y:0
                }
            },
            connection: {
                id1: "",
                id2: "",
            }
        };
    },
    mounted() {
        /* mounting our canvas */
        var c = document.getElementById("myCanvas");
        this.canvas = c.getContext("2d");
    },
    methods: {
        addMachine(){
            this.operation = "ADD MACHINE"
        },
        addQueue(){
            this.operation = "ADD QUEUE"
        },
        delet(){
            this.operation = "DELETE"
        },
        start(){
            var canvas = document.getElementById("myCanvas");
            var ctx = canvas.getContext("2d");
            ctx.fillStyle = 'black';
            ctx.beginPath();
            ctx.arc(100,400,40,0,2 * Math.PI);
            ctx.fill();
            ctx.fillStyle = "white";
            ctx.font = "20px Arial";
            ctx.fillText("start", 80,400);
            ctx.fillStyle = 'black';
            ctx.beginPath();
            ctx.arc(1400,400,40,0,2 * Math.PI);
            ctx.fill();
            ctx.fillStyle = "white";
            ctx.font = "20px Arial";
            ctx.fillText("end", 1380,400);
        }
        ,
        async dr(e){
            var canvas = document.getElementById("myCanvas");
            var ctx = canvas.getContext("2d");
            var x = e.offsetX;
            var y = e.offsetY;
            switch(this.operation){
                case "ADD MACHINE":{
                    this.machine.location.x = x;
                    this.machine.location.y = y;
                    console.log(this.machine.location);
                    const response = await axios.post("http://localhost:8095/add/machine/", this.machine.location);
                    this.diagram = response.data;
                           
                    
                    break;
                }
                case "ADD QUEUE":
                    {
                    this.queue.location.x = x;
                    this.queue.location.y = y;
                    console.log(this.queue);
                    const response = await axios.post("http://localhost:8095/add/queue/", this.queue.location);
                    this.diagram = response.data;
                    
                    
                    break;
                    }
                case "DELETE":
                    var i ;
                    for(i = 0 ; i<this.diagram.machines.length; ++i){
                    ctx.beginPath();
                    ctx.arc(this.diagram.machines[i].location.x,this.diagram.machines[i].location.y,50,0,2 * Math.PI);
                    ctx.closePath();
                    if (ctx.isPointInPath(x, y)){
                        console.log(this.diagram.machines[i].id);
                        const response = await axios.post("http://localhost:8095/remove/machine/", this.diagram.machines[i].id);
                        this.diagram = response.data;
                        break;
                        }
                    }
                    for(i = 0 ; i<this.diagram.queues.length; ++i){
                    ctx.beginPath();
                    ctx.rect(this.diagram.queues[i].location.x-40,this.diagram.queues[i].location.y-25,80,50);
                    ctx.closePath();
                    if (ctx.isPointInPath(x, y)){
                        console.log(this.diagram.queues[i].id);
                        const response = await axios.post("http://localhost:8095/remove/queue/", this.diagram.queues[i].id);
                        this.diagram = response.data;
                        break;
                        }
                    }
                    break;
                default:
                    break;
            }
            this.clear();
            this.operation=null;
            this.drawBoard();
            
        },
        drawBoard(){
            this.clear();
            // draw yellow conections (into machines)
            var canvas = document.getElementById("myCanvas");
            var ctx = canvas.getContext("2d");
            var i;
            ctx.strokeStyle= "yellow";
            for(i = 0 ; i<this.diagram.machinesInL.length; ++i){
                
                ctx.beginPath();
                ctx.moveTo(this.diagram.machinesInL[i].p1.x,this.diagram.machinesInL[i].p1.y);
                ctx.lineWidth =4;
                ctx.lineTo(this.diagram.machinesInL[i].p2.x,this.diagram.machinesInL[i].p2.y);
                ctx.stroke();
                ctx.lineWidth =1;
            }

            // draw blue connections (out of machines)
            ctx.strokeStyle= "blue";
            for(i = 0 ; i<this.diagram.machinesOutL.length; ++i){
                
                ctx.beginPath();
                ctx.moveTo(this.diagram.machinesOutL[i].p1.x,this.diagram.machinesOutL[i].p1.y);
                ctx.lineWidth =4;
                ctx.lineTo(this.diagram.machinesOutL[i].p2.x,this.diagram.machinesOutL[i].p2.y);
                ctx.stroke();
                ctx.lineWidth =1;
            }
            ctx.strokeStyle= "black";
            // draw machines
            for(i = 0 ; i<this.diagram.machines.length; ++i){
                console.log(this.diagram);
                if(this.diagram.machines[i].currProduct == -1) {
                ctx.fillStyle = "green";        
                } else{
                ctx.fillStyle = '#' + this.diagram.machines[i].currProduct.toString();
                }
                ctx.beginPath();
                ctx.arc(this.diagram.machines[i].location.x,this.diagram.machines[i].location.y,50,0,2 * Math.PI);
                ctx.fill();
                ctx.stroke();
                ctx.fillStyle = "black";
                ctx.font = "30px Arial";
                ctx.fillText("M"+this.mi, this.diagram.machines[i].location.x-20, this.diagram.machines[i].location.y+5);
                this.mi++;
            }
           
            // draw queues
            for(i = 0 ; i<this.diagram.queues.length; ++i){
                ctx.fillStyle = '#ff4040';
                ctx.fillRect(this.diagram.queues[i].location.x-40,this.diagram.queues[i].location.y-25,80,50);
                ctx.strokeRect(this.diagram.queues[i].location.x-40,this.diagram.queues[i].location.y-25,80,50);
                ctx.fillStyle = "black";
                ctx.font = "30px Arial";
                ctx.fillText("Q"+this.qi, this.diagram.queues[i].location.x-20, this.diagram.queues[i].location.y+10);
                this.qi++;
            }
            
            
        },
        async connect(){
            var mach;
            var que;
            var way = prompt("Choose the way of connection.\n1- machine to queue.\n2- queue to machine.");
            switch(way){
                case "1":{
                    mach=parseInt(prompt("Enter number of the machine."));

                    if(mach-1>=this.diagram.machines.length){
                        alert("invalid machine.");
                        return;
                    }
                    que= parseInt(prompt("Enter number of the queue."));
                    
                    if(que-1>=this.diagram.queues.length){
                        alert("invalid queue.");
                        return;
                    }
                    this.connection.id1=this.diagram.machines[mach-1].id;
                    this.connection.id2=this.diagram.queues[que-1].id;
                    console.log(this.connection);
                    const response = await axios.post("http://localhost:8095/connect/machine/queue/", this.connection);
                    this.diagram = response.data;
                    console.log("done");
                    console.log(this.diagram);
                    break;
                }   
                case "2":{
                    que= parseInt(prompt("Enter number of the queue."));
                    if(que-1>=this.diagram.queues.length){
                        alert("invalid queue.");
                        return;
                    }

                    mach=parseInt(prompt("Enter number of the machine."));
                    if(mach-1>=this.diagram.machines.length){
                        alert("invalid machine.");
                        return;
                    }
                    this.connection.id2=this.diagram.machines[mach-1].id;
                    this.connection.id1=this.diagram.queues[que-1].id;
                    console.log(this.connection);
                    const response = await axios.post("http://localhost:8095/connect/queue/machine/", this.connection);
                    this.diagram = response.data;
                    break;
                }
                default:
                    alert("INVALID");
                    break;
            }
            this.drawBoard();         
        },
        /* clear functions */
        clear() {
            var canvas = document.getElementById("myCanvas");
            var context = canvas.getContext("2d");
            context.clearRect(0, 0, canvas.width, canvas.height);
            this.mi=1;
            this.qi=1;
        },
        async clearRequest() {
            const response = await axios.get("http://localhost:8095/clear/");
            this.diagram = response.data;
            this.diagram = "";
            this.clear();
        },
        async startSim() {
            var products = prompt("Enter number of products you need to simulate");
            await axios.post(("http://localhost:8095/start/simulation/"), {
                numOfProducts: products
            });
            this.updateDiagram();
        },
        async updateDiagram() {
            for (let index = 0; index < 100; index++) {
                console.log("Test Update")
                var response = await axios.get("http://localhost:8095/get/updates/");
                this.diagram = (response.data);
                console.log(this.diagram);
                this.clear();
                this.operation=null;
                this.drawBoard();
            }
        },
    }
};
</script>

<style>
.main {
    background-image: url("./images/cool-background.png");
    background-size: cover;
}
.shapes {
    text-align: center;
}


.opt {
    display: inline-block;
    background: #9fdcff;
    padding: 0.35em 1.2em;
    border: 0.1em solid #000000;
    margin: 0 0.3em 0.3em 0;
    border-radius: 0.25cm;
    box-sizing: border-box;
    text-decoration: none;
    font-family: "Roboto", sans-serif;
    font-weight: 300;
    color: #2c1500;
    text-align: center;
    cursor: pointer;
}
.opt:hover {
    color: #fbff00;
    background-color: #09005a;
}
.smiul {
    display: inline-block;
    background: #ff0000;
    padding: 0.35em 1.2em;
    border: 0.1em solid #000000;
    margin: 0 0.3em 0.3em 0;
    border-radius: 0.25cm;
    box-sizing: border-box;
    text-decoration: none;
    font-family: "Roboto", sans-serif;
    font-weight: 300;
    color: #ffffff;
    text-align: center;
    cursor: pointer;
}
.smiul:hover {
    color: #000000;
    background-color: #00dd25;
}
@media all and (max-width: 30em) {
    .opt {
        display: block;
        margin: 0.4em auto;
    }
}
label {
    color: #ffffff;
    margin: 8px;
    margin-left: 20px;
}
.drawing-board {
    cursor:copy;
    background-color: rgb(255, 255, 255);
    position: relative;
    display: block;
    margin: auto;
    outline: rgb(82, 82, 82) 3px solid;
}
</style>