var i = 0;
function display(){
    var byId = document.getElementById("underDiv");
    if (i == 0){
        byId.style.display = "block";
        i = i + 1;
    }
    else if(byId.style.display=="none") {
        byId.style.display = "block";
    }else {
        byId.style.display = "none";
    }
}

function toAdd(){
    window.location.href="/travel/page/toAdd";
}

function jumpMain() {
    window.location.href = "/travel/page/main";
}
function toMyTravel(){
    window.location.href = "/travel/page/myTravels";
}

function selectByText(){
    var elementById = document.getElementById("selectInput").value;
    if(elementById==""){
        alert("您还没有输入搜索内容！");
    }else {
        window.location.href = "/travel/page/selectByLike/" + elementById;
    }
}

function exit(){
    window.location.href= "/travel/user/exit";
}