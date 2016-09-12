var IMG_PATH = "./resources/img/"
var CHAIR_FILE = "chair.jpg"
var TABLE_PATTERN_FILE = "table_pattern.jpg"

var source = "http://192.168.43.184:8080"
var GET_LAYOUT_URL = source + "/REST_MAVEN/webresources/mohit/getLayoutInfo";
var GET_DESK_URL = source + "/REST_MAVEN/webresources/mohit/getDeskInfo";
var GET_EMPLOYEE_URL = "";

var canvasElement = document.getElementById("workarea");
var canvas = new fabric.Canvas('workarea', { selection: false, backgroundColor: "#ffffff"});

// Defines all quantities wrt gridSize
var chairDef = {
	"gridsPerChair": 1,
	"paddingRatio" : 0.0625,
	"borderRadiusRatio" : 0.125
}