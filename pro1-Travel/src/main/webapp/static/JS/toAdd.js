var imgCount = 0;
function ProcessFile(e) {
    var file = document.getElementById('file').files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function (event) {
            var txt = event.target.result;

            var img = document.getElementById('filePng')
            img.src = txt;//将图片base64字符串赋值给img的src
            var errImg = aa("errAddImg");
            errImg.innerText="";
            document.getElementById("addFormBtn").disabled=false;
            imgCount = imgCount + 1;
        };
    }
    reader.readAsDataURL(file);
}

function contentLoaded() {
    document.getElementById('file').addEventListener('change',
        ProcessFile, false);
}

window.addEventListener("DOMContentLoaded", contentLoaded, false);


function checkTitle(){
    var tilte = aa("realAddTitle").value;
    if(tilte==""){
        var errTitle = aa("errTitle");
        errTitle.innerText = "请输入标题";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errTitle = aa("errTitle");
        errTitle.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}

function aa(id){
    return document.getElementById(id);
}
function checkRegion(){
    var region = aa("addRegionInput").value;
    if(region==""){
        var errRegion =   aa("errAddInput");
        errRegion.innerText = "请输入地区";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errRegion =  aa("errAddInput");
        errRegion.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}

function checkContext(){
    var context = aa("addTextInput").value;
    if(context==""){
        var errcontext = aa("errContext");
        errcontext.innerText = "请输入内容";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errcontext = aa("errContext");
        errcontext.innerText = "";
        document.getElementById("addFormBtn").disabled=false;
    }
}

function checkAddImg(){
    var img = aa("filePng");


    if(imgCount == 0){
        var errImg = aa("errAddImg");
        errImg.innerText="请放置图片";
        document.getElementById("addFormBtn").disabled=true;
    }else {
        var errImg = aa("errAddImg");
        errImg.innerText="";
        document.getElementById("addFormBtn").disabled=false;
    }


}

function addCheckAll(){
    var tilte = aa("realAddTitle").value;
    if(tilte==""){
        var errTitle = aa("errTitle");
        errTitle.innerText = "请输入标题";
        return false;
    }
    var region = aa("addRegionInput").value;
    if(region==""){
        var errRegion =   aa("errAddInput");
        errRegion.innerText = "请输入地区";
        return false;
    }
    var context = aa("addTextInput").value;
    if(context==""){
        var errcontext = aa("errContext");
        errcontext.innerText = "请输入内容";
        return  false;
    }
    var img = aa("filePng");

    if(imgCount==0){
        var errImg = aa("errAddImg");
        errImg.innerText="请放置图片";
        return false;
    }
    return true;
}