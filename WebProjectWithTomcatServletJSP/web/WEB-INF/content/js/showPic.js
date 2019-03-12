function showPic(whichpoint){
    if(!document.getElementById) return false;
    var imgSource = whichpoint.getAttribute("href");
    var imgTitle = whichpoint.getAttribute("title");
    var imgpoint = document.getElementById("showimg");
    imgpoint.setAttribute("src",imgSource);
    imgpoint.setAttribute("alt",imgTitle);
}