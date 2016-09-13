var canvas = new fabric.Canvas('workarea', {selection: false, defaultCursor: "move"});

function initialiseFromDummy(input){
  var inputObj = input;
  var floor = new Floor(inputObj);
  floor.createFloorLayout();
}

function initialise(){
  document.getElementById("loader").setAttribute('style', "display:block")
  $.ajax({url: GET_LAYOUT_URL, success: function(result){
    document.getElementById("loader").setAttribute('style', "display:hidden")
    var inputObj = result;
    $.ajax({url: GET_DESK_URL, success: function(result){
      var deskList = result;
      deskList.forEach(function(desk){
        workarea.createDesk(desk);
      });
    }})
  },
  error: function(error, statusText){
    document.getElementById("loader").setAttribute('style', "display:none");
    initialiseFromDummy(sampleInput);
  }});
}



  


 

  
window.onload = function(e){
//initialise();
initialise();
}