var sampleInput = {
  "layoutId": 1,
  "minimum_x": 0,
  "maximum_x": 17,
  "minimum_y": 0,
  "maximum_y": 17,
  "desks": [
  {
    "deskId": 38,
    "x": 10,
    "y": 12,
    "width": 1,
    "height": 2,
    "brid": ""
  },
  {
    "deskId": 39,
    "x": 10,
    "y": 14,
    "width": 1,
    "height": 2,
    "brid": ""
  },
  {
    "deskId": 40,
    "x": 10,
    "y": 16,
    "width": 1,
    "height": 2,
    "brid": ""
  }]
};

this.canvas = new fabric.Canvas('workarea', { selection: false, backgroundColor: "#ffffff"});


function parse(input){
  var inputObj = input;
  console.log(inputObj.tables);
  var workarea = new Workarea(inputObj.workarea.height, inputObj.workarea.width, inputObj.tables.length);
  inputObj.tables.forEach(function(table){
    workarea.createTable(table.x,table.y,table.width,table.height);
    table.chairs.forEach(function(chair){
      var angle = determineChairOrientation(table, chair);
      workarea.createChair(chair.x,chair.y,angle, chair.chairId);
    });
  });
}

function Workarea(height, width, noOfTables){
  this.height = height;
  this.width = width;
  this.gridSize = ((600/(height))<(600/(width+2*noOfTables))?(600/(height+2*noOfTables)):(600/(width+2*noOfTables)));
  console.log(this.gridSize)
  this.canvas = canvas;
  this.orientaionAdjustment = [[0,-1],[2,0],[1,2],[-1,1]]
  this.onMove();

}
  
  Workarea.prototype.createChair = function(x, y, angle, chairId){
    var that = this;
    console.log(x +" "+y);
    fabric.Image.fromURL(IMG_PATH + CHAIR_FILE, function(chair) {            
      chair.set({'width':that.gridSize});
      chair.set({'height':that.gridSize});
      chair.set({'left':(x + that.orientaionAdjustment[angle/90][0])*that.gridSize});
      chair.set({'top':(y + that.orientaionAdjustment[angle/90][1])*that.gridSize});
      chair.set({'hasControls': false});
      chair.set({'angle': angle});
      chair.set({'entity': 'chair'})
      chair.set({'chairId': chairId});
      that.canvas.add(chair);        
    });
  };

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

  Workarea.prototype.onMove = function(){
    var that = this;
    canvas.on('object:moving', function(options) { 
  var targ = options.target;
  targ.setCoords();
  options.target.set({
    left: Math.round(options.target.left / that.gridSize) * that.gridSize,
    top: Math.round(options.target.top / that.gridSize) * that.gridSize
  });
  
  var items = canvas.getObjects().filter(function(o){
        return targ !== o;
  });
  canvas.forEachObject(function (obj) {
        if (obj === options.target) return;

        // If objects intersect
        if (options.target.isContainedWithinObject(obj) || options.target.intersectsWithObject(obj) || obj.isContainedWithinObject(options.target)) {

            var distX = ((obj.getLeft() + obj.getWidth()) / 2) - ((options.target.getLeft() + options.target.getWidth()) / 2);
            var distY = ((obj.getTop() + obj.getHeight()) / 2) - ((options.target.getTop() + options.target.getHeight()) / 2);

            // Set new position
            findNewPos(distX, distY, options.target, obj);
        }
      });
});  
  };

  function determineChairOrientation(table, chair){
    if(chair.x == table.x && chair.y == table.y){
      if(table.width>table.height){
        return 0;
      }
      else{
        return 270;
      }
    }
    else if(chair.x == table.x &&chair.y == table.y+table.height-1){
      if(table.width>table.height){
        return 180;
      }
      else{
        return 270;
      }
    }
    else if(chair.x == table.x){
      return 270;
    }
    else if(chair.y == table.y && chair.x == table.x + table.width-1){
      if(table.width>table.height)
        return 0;
      else{
        return 90;
      }
    }
    else if(chair.y == table.y + table.height-1 && chair.x == table.x + table.width-1){
      if(table.width>table.height){
        return 180;
      }
      else{
        return 90;
      }
    }
    else if(chair.y == table.y){
      return 0;
    }
    else if(chair.y == table.y + table.height-1){
      return 180;
    }
    else if(chair.x == table.x + table.width-1){
      return 90;
    }
  }

  var tooltipSpan = document.getElementById('tooltip-span');

 canvas.on('mouse:over', function(e) {
    console.log(e.target);
    if(e.target.entity == "chair"){
      var element = e.target;
      /*
      canvas.add(new fabric.Rect({
        left:10,
        top:10,
        width:100,
        height:50,
        fill: 'orange',
        innerText:"Chair Id: " + e.target.chairId 
      }))
    }
    */var style = "display:block; position:fixed; overflow:hidden; background-color: black; color:white; padding:10px; box-shadow: 1px 1px 2px grey; border-radius:5px"
      tooltipSpan.setAttribute("style", style);
      tooltipSpan.innerHTML = "Chair Id: " + e.target.chairId;
      canvas.renderAll();
    }
  });

  canvas.on('mouse:out', function(e) {
    tooltipSpan.setAttribute("style", "display:none;")
    canvas.renderAll();
  });

  window.onmousemove = function (e) {
    var x = e.clientX,
        y = e.clientY;
    tooltipSpan.style.top = (y + 20) + 'px';
    tooltipSpan.style.left = (x + 20) + 'px';
}; 

parse(sampleInput)


function findNewPos(distX, distY, target, obj) {
    // See whether to focus on X or Y axis
    if(Math.abs(distX) > Math.abs(distY)) {
        if (distX > 0) {
            target.setLeft(obj.getLeft() - target.getWidth());
        } else {
            target.setLeft(obj.getLeft() + obj.getWidth());
        }
    } else {
        if (distY > 0) {
            target.setTop(obj.getTop() - target.getHeight());
        } else {
            target.setTop(obj.getTop() + obj.getHeight());
        }
    }
}

// snap to grid

