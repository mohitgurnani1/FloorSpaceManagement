var canvas = new fabric.Canvas('workarea', {selection: false, defaultCursor: "move"});

function initialiseFromDummy(input){
  var inputObj = input;
  var floor = new Floor(inputObj);
  floor.createFloorLayout();
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



  


 

  
window.onload = function(e){
//initialise();
initialiseFromDummy(sampleInput);
}