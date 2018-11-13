function back(){
    api.closeWin();
}

function openWin(path){
    api.openWin({
        name: path,
        url: path,
    });
}
