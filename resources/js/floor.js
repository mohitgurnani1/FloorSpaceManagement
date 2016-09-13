function Floor(floorJSON){
  this.leftTopPoint = {x: floorJSON.minimum_x, y: floorJSON.minimum_y};
  this.rightBottomPoint = {x: floorJSON.maximum_x, y: floorJSON.maximum_y};
  this.height = this.rightBottomPoint.y - this.leftTopPoint.y
  this.width = this.rightBottomPoint.x - this.leftTopPoint.x

  ENV.xGridSize = window.innerWidth/this.width;
  ENV.yGridSize = window.innerHeight/this.height; 
  ENV.gridSize = (ENV.xGridSize<ENV.yGridSize)?(ENV.xGridSize):(ENV.yGridSize);
  this.tables = floorJSON.tables;

  console.log("Floor created.")
}

Floor.prototype.createFloorLayout = function(){
  this.setupFloor();
  this.tables.forEach(function(tableJSON){
    var table = new Table(tableJSON);
    console.log("Creating Table Layouts")
    table.createTableLayout();
  });
};

Floor.prototype.setupFloor = function(){
  canvas.setBackgroundColor({source: IMG_PATH + FLOOR_PATTERN_FILE, repeat: 'repeat'}, function(){
    canvas.renderAll();
  });
}
