/*Dingming Feng*/

var pet = new Array("../image/1.JPG","../image/2.JPG","../image/3.JPG");
var petNum = pet.length;
var i = 0;
setInterval("picPlay()",3000);
function picPlay() {
    document.getElementById("photos").innerHTML  = "<img src='"+pet[i]+"' width=403px height=302px>";        
        i++;
        if(i>=petNum)  i=0;
}

function add(obj) {
    var events = prompt("Which classes do you want to take?", "");
    var tnode1=document.createTextNode(events);
    var now = new Date();
    var tnode2=document.createTextNode("  Class added on ("+now.getMonth()+"/"+now.getDate()+"/"+(now.getYear()+1900)+")");
    newchild = obj.cloneNode(false);
    newchild.setAttribute("onclick", "take(this)");
	newchild.setAttribute("class", "o");
	newchild.appendChild(tnode1);
	newchild.appendChild(tnode2);
	obj.parentNode.appendChild(newchild);
}

function take(obj){
	if (obj.getAttribute("class")=="notTaken") {
		obj.setAttribute("class", "taken");
	}
	else {
		obj.setAttribute("class", "notTaken");
	}
}

function both(){  
    add();  
    take();  
} 
