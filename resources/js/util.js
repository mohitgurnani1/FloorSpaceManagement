/* static paths */
var IMG_PATH = "./resources/img/";
var CHAIR_FILE = "chair.png";
var TABLE_PATTERN_FILE = "table_pattern.jpg";
var FLOOR_PATTERN_FILE = "floor_pattern.jpg";

/* API paths */
var source = "http://192.168.43.184:8080"
var GET_LAYOUT_URL = source + "/REST_MAVEN/webresources/mohit/getLayoutInfo";
var GET_DESK_URL = source + "/REST_MAVEN/webresources/mohit/getDeskInfo";
var GET_EMPLOYEE_URL = "";



// Defines all quantities wrt gridSize
var ENV = {
	gridsPerChair: 1,
	tableBorderRadiusRatio : 0.125
};