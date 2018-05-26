efform_onload = function () {
	efbutton.setButtonStatus("insert",true);
	efbutton.setButtonStatus("update",true);
	efbutton.setButtonStatus("delete",true);
	efbutton.setButtonStatus("submit",true); 
} 
function button_query_onclick(){
	efgrid.submitInqu("ef_grid_result", "PF", "${serviceName}", "query",true); 
}
function button_insert_onclick(){
	efgrid.submitForm("ef_grid_result", "PF", "${serviceName}", "insert",true);
	button_query_onclick();
}
function button_update_onclick(){
	efgrid.submitForm("ef_grid_result", "PF", "${serviceName}", "update",true);
	button_query_onclick();
}
function button_delete_onclick(){
	efgrid.submitForm("ef_grid_result", "PF", "${serviceName}", "delete",true);
	button_query_onclick();
}