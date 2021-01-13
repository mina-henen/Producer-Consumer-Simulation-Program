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
            <button class="opt" title="Connect">Connect</button>
            <button class="opt" title="disconnect">Disconnect</button>
            <button class="opt" @click="setMove" title="move" >Move</button>
            <button class="opt" @click="setDelete" title="delete">Delete</button>
            <button class="opt" @click="clear" title="clear screen">Clear</button>
        </div>
        <div>
            <button class="smiul" title="Start">Start simulation</button>
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
            type: null,
            mi:1,
            qi:1,
            currBoardIndex: 0,
            shapes: [null],
            /* shapes structure that carry whole shape data */
            shapeStruct: {
                points: [],
                shapeType: "null",
                colour: "null",
                indexInBoard: "-1",
                stroke: "null",
                strokeWidth: "null",
                length: "null",
                width: "null",
                sideLength: "null",
                hRadius: "null",
                vRadius: "null",
                radius: "null"
            },
            operation: "null",
            numOfShapes: 0,
            canvas: null,
            selectedShape: false,
            selShape: "null",
            xBefMov: 0,
            yBefMov: 0,
            movedX: 0,
            movedY: 0,
            oder: "null",
            resizeRatio: 1,
            diagram: "",
            machine: {
                location:"",
            },
            queue: {
                location:""
            }
        };
    },
    mounted() {
        /* mounting our canvas */
        var c = document.getElementById("myCanvas");
        this.canvas = c.getContext("2d");
        this.start();
    },
    methods: {
        addMachine(){
            this.type = "m"
        },
        addQueue(){
            this.type = "q"
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
            switch(this.type){
                case "m":
                    this.machine.location.x = x;
                    this.machine.location.y = y;
                    const response = await axios.post("http://localhost:8095/add/machine/", this.machine);
                    this.diagram = response.data;         
                    ctx.fillStyle = '#38ff78';
                    ctx.beginPath();
                    ctx.arc(x,y,50,0,2 * Math.PI);
                    ctx.fill();
                    ctx.stroke();
                    ctx.fillStyle = "black";
                    ctx.font = "30px Arial";
                    ctx.fillText("M"+this.mi, x-20, y+5);
                    this.mi++;
                    break;
                case "q":
                    this.queue.location.x = x;
                    this.queue.location.y = y;
                    ctx.fillStyle = '#ff4040';
                    ctx.fillRect(x-40,y-25,80,50);
                    ctx.strokeRect(x-40,y-25,80,50);
                    ctx.fillStyle = "black";
                    ctx.font = "30px Arial";
                    ctx.fillText("Q"+this.qi, x-20, y+10);
                    this.qi++;
                    break;
                default:
                    break;
            }
            this.type=null;
            
        },
        
        /* clear functions */
        clear() {
            var canvas = document.getElementById("myCanvas");
            var context = canvas.getContext("2d");
            context.clearRect(0, 0, canvas.width, canvas.height);
            this.mi=1;
            this.qi=1;
            this.start();
        },

        /************************************************************************ OLD CODE *********************************************************************/
        /* function to detect the selected point by mouse click using mouse event (e)*/
        async setPoint(e) {
            if (this.selectedShape == true) {
                var canvas = document.getElementById("myCanvas");
                var ctx = canvas.getContext("2d");
                var x = e.offsetX;
                var y = e.offsetY;
                ctx.beginPath();
                ctx.arc(x, y, 2, 0, 2 * Math.PI);
                ctx.fill();
                this.shapeStruct.points.push({ x: x, y: y });
                if (
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "RECTANGLE") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "SQUARE") ||
                    (this.shapeStruct.points.length == 2 &&
                        this.shapeStruct.shapeType == "LINE") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shapeStruct.shapeType == "TRIANGLE") ||
                    (this.shapeStruct.points.length == 3 &&
                        this.shapeStruct.shapeType == "ELLIPSE")
                ) {
                    await this.sendRequest();
                } else if (
                    this.shapeStruct.points.length == 2 &&
                    this.shapeStruct.shapeType == "CIRCLE"
                ) {
                    this.shapeStruct.points.push(this.shapeStruct.points[1]);
                    await this.sendRequest();
                }
            }
        },
        /* function to draw the shape from the shape structure data that came from the back-end project */
        async drawShapes() {
            var canvas = document.getElementById("myCanvas");
            if (canvas.getContext) {
                var ctx = canvas.getContext("2d");
                ctx.fillStyle = this.shapeStruct.colour;
                ctx.strokeStyle = this.shapeStruct.stroke;
                switch (this.shapeStruct.shapeType) {
                    case "RECTANGLE":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.width,
                            this.shapeStruct.length
                        );
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[0].y,
                                this.shapeStruct.width,
                                this.shapeStruct.length
                            );
                        }
                        this.shapeStruct.shapeType = "RECTANGLE";
                        break;
                    case "SQUARE":
                        ctx.fillRect(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.width,
                            this.shapeStruct.length
                        );
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.strokeRect(
                                this.shapeStruct.points[0].x,
                                this.shapeStruct.points[0].y,
                                this.shapeStruct.width,
                                this.shapeStruct.length
                            );
                        }
                        this.shapeStruct.shapeType = "SQUARE";
                        break;
                    case "CIRCLE":
                        ctx.beginPath();
                        ctx.arc(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.radius,
                            0,
                            2 * Math.PI
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "CIRCLE";
                        break;
                    case "TRIANGLE":
                        ctx.beginPath();
                        ctx.moveTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[1].x,
                            this.shapeStruct.points[1].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[2].x,
                            this.shapeStruct.points[2].y
                        );
                        ctx.lineTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "TRIANGLE";
                        break;
                    case "ELLIPSE":
                        ctx.beginPath();
                        ctx.ellipse(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y,
                            this.shapeStruct.hRadius,
                            this.shapeStruct.vRadius,
                            0,
                            0,
                            2 * Math.PI
                        );
                        ctx.fill();
                        if (this.shapeStruct.strokeWidth > 0) {
                            ctx.lineWidth = this.shapeStruct.strokeWidth;
                            ctx.stroke();
                        }
                        this.shapeStruct.shapeType = "ELLIPSE";
                        break;
                    case "LINE":
                        ctx.beginPath();
                        ctx.moveTo(
                            this.shapeStruct.points[0].x,
                            this.shapeStruct.points[0].y
                        );
                        ctx.lineWidth =
                            this.shapeStruct.strokeWidth > 0
                                ? this.shapeStruct.strokeWidth
                                : 1;
                        ctx.lineTo(
                            this.shapeStruct.points[1].x,
                            this.shapeStruct.points[1].y
                        );
                        ctx.stroke();
                        this.shapeStruct.shapeType = "LINE";
                        break;
                    default:
                }
            }
        },
        /* function that send the data from front-end to back-end to process the data ad draw the shapes */
        async sendRequest() {
            var color = document.getElementById("myColor");
            var stroke = document.getElementById("myStroke");
            var stWidth = document.getElementById("strokeWidth");
            this.shapeStruct.colour = color.value;
            this.shapeStruct.stroke = stroke.value;
            this.shapeStruct.strokeWidth = stWidth.value;
            const response = await axios.post("http://localhost:8095/shapes/", {
                shape: this.shapeStruct,
                operation: "CREATE"
            });
            this.shapes = response.data;
            this.clear();
            if (this.shapes.length != 0) {
                this.shapes.forEach(element => {
                    if (element != null) {
                        this.shapeStruct = element;
                        this.drawShapes();
                    }
                });
            }
            this.selectedShape = false;
            this.currBoardIndex++;
        },
        /* function to select the shape to perform the shape operations on it using mouse event (e) */
        async select(e) {
            var x = e.offsetX;
            var y = e.offsetY;
            var c = document.getElementById("myCanvas");
            var ctx = c.getContext("2d");
            for (var i = this.shapes.length - 1; i >= 0; --i) {
                ctx.beginPath();
                if (this.shapes[i] == null) continue;
                switch (this.shapes[i].shapeType) {
                    case "RECTANGLE":
                    case "SQUARE":
                        ctx.rect(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].width,
                            this.shapes[i].length
                        );
                        break;
                    case "CIRCLE":
                        ctx.arc(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].radius,
                            0,
                            2 * Math.PI
                        );
                        break;
                    case "TRIANGLE":
                        ctx.moveTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[1].x,
                            this.shapes[i].points[1].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[2].x,
                            this.shapes[i].points[2].y
                        );
                        ctx.lineTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        break;
                    case "ELLIPSE":
                        ctx.ellipse(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y,
                            this.shapes[i].hRadius,
                            this.shapes[i].vRadius,
                            0,
                            0,
                            2 * Math.PI
                        );
                        break;
                    case "LINE":
                        ctx.moveTo(
                            this.shapes[i].points[0].x,
                            this.shapes[i].points[0].y
                        );
                        ctx.lineWidth =
                            this.shapes[i].strokeWidth > 0
                                ? this.shapes[i].strokeWidth
                                : 1;
                        ctx.lineTo(
                            this.shapes[i].points[1].x,
                            this.shapes[i].points[1].y
                        );
                        if (ctx.isPointInStroke(x, y)) {
                            this.selShape = this.shapes[i].indexInBoard;
                            this.selShape = i;
                            this.xBefMov = x;
                            this.yBefMov = y;
                        }
                        break;
                    default:
                }
                ctx.closePath();
                if (ctx.isPointInPath(x, y)) {
                    this.selShape = this.shapes[i].indexInBoard;
                    this.selShape = i;
                    this.xBefMov = x;
                    this.yBefMov = y;
                    break;
                }
            }
        },
        /* function to perform the selected operation on the selected shape and send data to back-end
        then recieve the processed data and re-draw the new shape with the new creatures  */
        async performOrder(e) {
            this.movedX = e.offsetX - this.xBefMov;
            this.movedY = e.offsetY - this.yBefMov;
            console.log(this.movedY);
            var response = null;
            switch (this.oder) {
                case "COPY":
                    console.log(this.selShape);
                    response = await axios.post("http://localhost:8095/copy/", {
                        shapeIndex: this.selShape,
                        deltaX: this.movedX,
                        deltaY: this.movedY
                    });
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "MOVE":
                    response = await axios.post("http://localhost:8095/move/", {
                        shapeIndex: this.selShape,
                        deltaX: this.movedX,
                        deltaY: this.movedY
                    });
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "DELETE":
                    response = await axios.post(
                        "http://localhost:8095/delete/",
                        {
                            shapeIndex: this.selShape
                        }
                    );
                    this.shapes = response.data;
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                case "RESIZE":
                    console.log(this.selShape);
                    response = await axios.post(
                        "http://localhost:8095/resize/",
                        {
                            shapeIndex: this.selShape,
                            ratio: this.resizeRatio
                        }
                    );
                    this.shapes = response.data;
                    console.log(response.data);
                    this.clear();
                    if (this.shapes.length != 0) {
                        this.shapes.forEach(element => {
                            if (element != null) {
                                this.shapeStruct = element;
                                this.drawShapes();
                            }
                        });
                    }
                    this.selectedShape = false;
                    this.selShape = null;
                    this.currBoardIndex++;
                    this.oder = null;
                    break;
                default:
            }
            this.setcurrIndex();
        },
        /* function to save the data in file */
        async save(x) {
            var path = prompt("Enter save path followed by name like\n \"C:\\Users\\Dell\\Desktop\\test.json\"");
            await axios.post("http://localhost:8095/save/", {
                name: path,
                fileType: x
            });
            this.setcurrIndex();
        },
        /* function to load data from file */
        async load(x) {
            var path = prompt("Enter path of the file that want to bel loaded like\n \"C:\\Users\\Dell\\Desktop\\test.json\"");
            const response = await axios.post("http://localhost:8095/load/", {
                name: path,
                fileType: x
            });
            this.shapes = response.data;
            this.clear();
            if (this.shapes.length != 0) {
                this.shapes.forEach(element => {
                    if (element != null) {
                        this.shapeStruct = element;
                        this.drawShapes();
                    }
                });
            }
            this.selectedShape = false;
            this.setcurrIndex();
        },
        /* function to connect between bac and front-end to set the current board */ 
        async setcurrIndex() {
            const response = await axios.get("http://localhost:8095/index/set");
            this.currBoardIndex = response.data;
        },
        /* undo function */
        async undo() {
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                choice: "UNDO"
            });
            this.shapes = response.data;
            this.clear();
            this.shapes.forEach(element => {
                if (element != null) {
                    this.shapeStruct = element;
                    this.drawShapes();
                }
            });
            this.selectedShape = false;
            // if (this.currBoardIndex > 0) {
            //     this.currBoardIndex--;
            // }
            this.setcurrIndex();
        },
        /* redo function */
        async redo() {
            const response = await axios.post("http://localhost:8095/undo/", {
                currBoardIndex: this.currBoardIndex,
                choice: "REDO"
            });
            this.shapes = response.data;
            this.clear();
            this.shapes.forEach(element => {
                if (element != null) {
                    this.shapeStruct = element;
                    this.drawShapes();
                }
            });
            this.selectedShape = false;
            this.setcurrIndex();
        },
        /* functions to set the shape that will be drawn */
        setRectangle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "RECTANGLE";
            this.selectedShape = true;
        },
        setSquare() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "SQUARE";
            this.selectedShape = true;
        },
        setCircle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "CIRCLE";
            this.selectedShape = true;
        },
        setLine() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "LINE";
            this.selectedShape = true;
        },
        setEllipse() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "ELLIPSE";
            this.selectedShape = true;
        },
        setTriangle() {
            this.shapeStruct.points = [];
            this.shapeStruct.shapeType = "TRIANGLE";
            this.selectedShape = true;
        },
        /* functions to set the operations that will be acted on the shape */
        setCopy() {
            this.oder = "COPY";
        },
        setMove() {
            this.oder = "MOVE";
        },
        setDelete() {
            this.oder = "DELETE";
        },
        setResize() {
            this.oder = "RESIZE";
            this.resizeRatio = prompt("Enter resize ratio please");
        }
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
