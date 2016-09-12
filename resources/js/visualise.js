var sampleInput = {
  "layoutId": 1,
  "minimum_x": 0,
  "maximum_x": 19,
  "minimum_y": 0,
  "maximum_y": 23,
  "tables": [{
    "x": 1,
    "y": 1,
    "width": 3,
    "height": 2,
    "desks":[[{
      "deskid": 1,
      "brid": ""
    },
    {
      "deskid": 2,
      "brid": ""
    },
    {
      "deskid": 2,
      "brid": ""
    }],
    [{
      "deskid": 3,
      "brid": ""
    },
    {
      "deskid": 4,
      "brid": ""
    }]]
  },
  {
    "x": 10,
    "y": 10,
    "width": 2,
    "height": 4,
    "desks":[[{
      "deskid": 1,
      "brid": ""
    },
    {
      "deskid": 2,
      "brid": ""
    }],
    [{
      "deskid": 3,
      "brid": ""
    },
    {
      "deskid": 4,
      "brid": ""
    }]]
  }]
};


canvasElement.setAttribute("width", window.innerWidth);
canvasElement.setAttribute("height", window.innerHeight);

var canvas = new fabric.Canvas('workarea', { selection: false, backgroundColor: "#ffffff"});

function initialiseFromDummy(input){
  var inputObj = input;
  var workarea = new Workarea(inputObj.minimum_x, inputObj.maximum_x, inputObj.minimum_y, inputObj.maximum_y, inputObj.tables.length);
  inputObj.tables.forEach(function(table){
    var orientation = (table.height<table.width)?"horizontal":"vertical";
    workarea.createTableLayout(table, orientation);

  });
}

function initialise(){

  $.ajax({url: GET_LAYOUT_URL, success: function(result){

    var inputObj = result;
    var workarea = new Workarea(inputObj.minimum_x, inputObj.maximum_x, inputObj.minimum_y, inputObj.maximum_y);
    $.ajax({url: GET_DESK_URL, success: function(result){
      var deskList = result;
      deskList.forEach(function(desk){
        workarea.createDesk(desk);
      });
    }})
  },
  error: function(error, statusText){
    alert(statusText);
  }});
}

function Workarea(minX, maxX, minY, maxY, noOfTables){
  this.minX = minX;
  this.minY = minY;
  this.maxX = maxX;
  this.maxY = maxY;
  this.height = maxY - minY;
  this.width = maxX - minY;
  this.canvas = canvas;
  this.gridSize = ((window.innerHeight/(this.height + 2*noOfTables))<(window.innerWidth/(this.width + 2*noOfTables))?(window.innerHeight/(this.height + 2*noOfTables))+3:(window.innerWidth/(this.width + 2*noOfTables))+3);
}


Workarea.prototype.createTable = function(x,y,width,height){
  var that = this;
  fabric.util.loadImage(IMG_PATH + TABLE_PATTERN_FILE, function(img) {
    that.canvas.add(new fabric.Rect({ 
      left: x*that.gridSize, 
      top: y*that.gridSize,  
      width: width*that.gridSize,
      height:height*that.gridSize,
      fill: '#9f9', 
      originX: 'left', 
      originY: 'top',
      rx : 20,
      ry: 20,
      hasControls: false,
      selection: false,
      fill: new fabric.Pattern({source:img})
    }));
  });  
};

Workarea.prototype.createTableLayout = function(table, orientation){
  this.createTable(table.x, table.y, table.width, table.height);
  var desks = table.desks;
  var chairLayout = {"orientation": orientation, "desks": desks};
  for(var rowNo=0; rowNo<desks.length; rowNo++){
    if(orientation == "horizontal"){
      var noOfDesksOnThisSide = table.desks[rowNo].length;
      chairLayout.sidePaddingForChair = (table.width - noOfDesksOnThisSide*chairDef.gridsPerChair)/(2*noOfDesksOnThisSide);
      chairLayout.variableCoordinate = table.x;
      chairLayout.fixedCoordinate = table.y;
      if(rowNo == 1){chairLayout.fixedCoordinate += table.height; }
      else{chairLayout.fixedCoordinate -= 1;}
      this.createChairsOnOneSide(chairLayout);
    }
    else if(orientation == "vertical"){
      var noOfDesksOnThisSide = table.desks[rowNo].length;
      chairLayout.sidePaddingForChair = (table.height - noOfDesksOnThisSide*chairDef.gridsPerChair)/(2*noOfDesksOnThisSide);
      chairLayout.variableCoordinate = table.y;
      
      chairLayout.fixedCoordinate = table.x;
      if(rowNo == 1){chairLayout.fixedCoordinate += table.width}
      else{chairLayout.fixedCoordinate -= 1;}
      this.createChairsOnOneSide(chairLayout);
    }
  }
};

Workarea.prototype.createChairsOnOneSide = function(chairLayout){
  var variableCoordinate = chairLayout.variableCoordinate;
  console.log(chairLayout.sidePaddingForChair)
  for(var deskNo=0; deskNo<chairLayout.desks.length; deskNo++){
    variableCoordinate += chairLayout.sidePaddingForChair;
    if(chairLayout.orientation == "horizontal"){
      this.createChair(variableCoordinate, chairLayout.fixedCoordinate, 0, chairLayout.desks[deskNo].deskid, "");

    }
    else if(chairLayout.orientation == "vertical"){
      this.createChair(chairLayout.fixedCoordinate, variableCoordinate, 0, chairLayout.desks[deskNo].deskid, "");
    }
    variableCoordinate += chairDef.gridsPerChair + chairLayout.sidePaddingForChair;
  }
};

Workarea.prototype.createChair = function(x, y, angle, chairId, brid){
  var that = this;
  fabric.Image.fromURL(IMG_PATH + CHAIR_FILE, function(chair) {            
    chair.set({'width':that.gridSize});
    chair.set({'height':that.gridSize});
    chair.set({'left':x*that.gridSize});
    chair.set({'top':y*that.gridSize});
    chair.set({'hasControls': false});
    chair.set({'angle': angle});
    chair.set({'entity': 'chair'})
    chair.set({'chairId': chairId});
    chair.set({'brid': brid});
    that.canvas.add(chair);        
  });
};

  Workarea.prototype.createDesk = function(desk){
    var that = this;
    that.canvas.add(new fabric.Rect({ 
      left: (desk.x)*that.xGridSize+chairDef.paddingRatio*that.gridSize, 
      top: (desk.y)*that.yGridSize+chairDef.paddingRatio*that.gridSize,  
      width: (desk.width)*that.xGridSize-2*chairDef.paddingRatio*that.gridSize,
      height: (desk.height)*that.yGridSize-2*chairDef.paddingRatio*that.gridSize,
      stroke: '#000000',
      fill: 'transperant',
      rx: chairDef.borderRadiusRatio*that.gridSize,
      ry: chairDef.borderRadiusRatio*that.gridSize,
      originX: 'left',
      originY: 'top',
      deskId: desk.deskId,
      brid: desk.brid,
      hasControls: false,
      entity: "desk",
      selectable: false,
      hoverCursor: "crosshair"
    }));
  };



  var tooltipSpan = document.getElementById('tooltip-span');
  var deskInfo = document.getElementById('desk-info');
  var employeeInfo = document.getElementById('employee-info');


 canvas.on('mouse:over', function(e) {
     if(e.target.entity == "desk"){
      var element = e.target;
      e.target.set({fill:"#ccc"})
      var style = "display:block; position:fixed; overflow:hidden; background-color: black; color:white; padding:10px; box-shadow: 1px 1px 2px grey; border-radius:5px"
      tooltipSpan.setAttribute("style", style);
      deskInfo.innerHTML = "Desk Id: " + e.target.deskId;
      
      if(e.target.brid == ""){
        deskInfo.innerHTML += "<br>Not Alloted";
      }
      else{
        employeeInfo.innerHTML = "Loading..."
      
        $.ajax({url: GET_EMPLOYEE_URL, success: function(result){
          employeeInfo.innerHTML = "BRID: " + result.brid + "<br>Employee Name: " + result.name + "<br>Date of Occupancy: " + result.doo + "<br>Team: " + result.team;
        },
        error: function(error){
          employeeInfo.innerHTML="Error Fetching Employee Information";
        }});
      }
      canvas.renderAll();
    }
  });

  canvas.on('mouse:out', function(e) {
    tooltipSpan.setAttribute("style", "display:none;")
    e.target.set({fill:"transperant"})
    canvas.renderAll();
  });

  window.onmousemove = function (e) {
    var x = e.clientX,
        y = e.clientY;
    tooltipSpan.style.top = (y + 20) + 'px';
    tooltipSpan.style.left = (x + 20) + 'px';
}; 

//initialise();
initialiseFromDummy(sampleInput);