let socket = new WebSocket("ws://10.2.1.48:9000/");
let listOpenChat = [];

// Connection opened
socket.addEventListener("open", (event) => {
    //socket.send(document.getElementById("id").textContent);
    if(document.getElementById("idDest").textContent === "") {
        //console.log("role: " + document.getElementById("role").textContent);
        socket.send(JSON.stringify({
            id: document.getElementById("id").textContent,
            fullname: document.getElementById("fullname").textContent,
            role: document.getElementById("role").textContent,
            request: "connect"
        }));
    }else{
        socket.send(JSON.stringify({
            id: document.getElementById("id").textContent,
            fullname: document.getElementById("fullname").textContent,
            idDest: document.getElementById("idDest").textContent,
            fullnameDest: document.getElementById("fullnameDest").textContent,
            request: "connectNewChat"
        }));
    }
});

function compareFnc(a, b) {
    let time1 = parseInt(a.timestamp);
    let time2 = parseInt(b.timestamp);
    if (time1 < time2) {
        return -1;
    }
    if (time1 > time2) {
        return 1;
    }
    // a must be equal to b
    return 0;
}

function addMessage(myId, idSender, timestamp, message, ul){
    let liMessage = document.createElement("li");
    liMessage.classList.add("clearfix");

    let divTime = document.createElement("div");
    divTime.classList.add("message-data");
    if(myId == idSender)
        divTime.classList.add("text-right");

    let spanTime = document.createElement("span");
    spanTime.classList.add("message-data-time");

    let time = parseInt(timestamp);
    let date = new Date(time);
    let day = String(date.getDate()).padStart(2, '0');
    let month = String(date.getMonth() + 1).padStart(2, '0');
    let year = date.getFullYear();
    let hours = String(date.getHours()).padStart(2, '0');
    let minutes = String(date.getMinutes()).padStart(2, '0');
    spanTime.textContent = day + "/" + month + "/" + year + " " + hours + ":" + minutes;

    divTime.appendChild(spanTime);

    let divText = document.createElement("div");
    divText.classList.add("message");
    if(myId == idSender) {
        divText.classList.add("other-message");
        divText.classList.add("float-right");
    }else{
        divText.classList.add("my-message");
    }
    divText.textContent = message;

    liMessage.appendChild(divTime);
    liMessage.appendChild(divText);
    ul.appendChild(liMessage);
}

function addNameToList(id, name, active, notify){
    let ul = document.getElementById("chatList");
    let li = document.createElement("li");
    li.classList.add("clearfix");
    li.classList.add("nameChat");
    li.classList.add("about");
    li.classList.add("name");
    li.id = 'idList:'+ id;
    li.innerText = name;

    let functionCall = "requestMessages(" + id + ")";
    li.setAttribute('onclick', functionCall);

    if(active)
        li.classList.add("active");

    let i = document.createElement("i");
    i.classList.add("fa");
    i.classList.add("fa-exclamation");

    if(notify)
        i.classList.add("showingNotification");

    li.appendChild(i);
    ul.appendChild(li);
}

function addNotification(id, nameSender, timestamp, notification){
    let ul = document.getElementById("chatList");
    document.getElementById("plist").removeChild(ul);
    let ulNew = document.createElement("ul");
    ulNew.classList.add("list-unstyled");
    ulNew.classList.add("chat-list");
    ulNew.classList.add("mt-2");
    ulNew.id = "chatList";
    let structElem = {
        'id': id,
        'name': nameSender,
        'timestamp': timestamp,
        'active': !notification,
        'notification': notification
    };
    document.getElementById("plist").appendChild(ulNew);
    listOpenChat.unshift(structElem);
    addNameToList(listOpenChat[0].id, listOpenChat[0].name, listOpenChat[0].active, listOpenChat[0].notification);
    let len = listOpenChat.length;
    let idToDelete = -1;
    for(let i=1; i<len; i++) {
        if (listOpenChat[i].id === id) {
            idToDelete = i;
        }else {
            addNameToList(listOpenChat[i].id, listOpenChat[i].name, listOpenChat[i].active, listOpenChat[i].notification);
        }
    }
    let x = 0;
    if(idToDelete!=-1) {
         x = listOpenChat.splice(idToDelete, 1);
         //console.log(x.name);
    }
}

// Listen for messages
socket.addEventListener("message", (event) => {
    var myId = document.getElementById("id").textContent;
    var myRole = document.getElementById("role").textContent;
    //console.log("Message from server ", event.data);
    var regex = /\{([^\{}]*)}/g;
    var results=[], m, x;
    while ( m = regex.exec(event.data.toString()) ) {
        results.push(m[1]);
    }
    var names=[];
    var idList=[];
    //console.log(results);
    if(results[0]=="sendList"){
        var dataRegex = /\["([^\][]*)"]/g;
        //var ul = document.getElementById("chatList");
        for(let i=1; i<results.length; i++) {
            var index = 0;
            while (x = dataRegex.exec(results[i])) {
                if (index % 2 == 0)
                    names.push(x[1]);
                else
                    idList.push(x[1]);
                index++;
            }
        }

        for(let i=0; i<names.length; i++){
            let structList = {
                'id': idList[i],
                'name': names[i],
                'timestamp': "0",
                'active': false,
                'notification': false
            }
            listOpenChat.push(structList);
            addNameToList(idList[i], names[i], false, false);
        }

        let idDest = document.getElementById("idDest").textContent;
        let fullnameDest = document.getElementById("fullnameDest").textContent;
        if(idDest != ""){
            let nameList = document.getElementById("idList:"+idDest);
            if(nameList==null){
                addNameToList(idDest, fullnameDest, true);
            }
            requestMessages(document.getElementById("idDest").textContent);
        }
    }
    else if(results[0]=="fullChat"){
        var messages = [];
        for(let i=1; i<results.length; i++) {
            let array = results[i].split('"');
            let structElement = {
                'idSender': array[1],
                'roleSender': array[3],
                'idDest': array[5],
                'message': array[7],
                'timestamp': array[9]
            }
            messages.push(structElement);
        }

        let destId = document.getElementById("idDest").textContent;
        let destName = document.getElementById("fullnameDest").textContent;
        let chat = document.getElementsByClassName("showing");
        if(chat.length > 0){
            chat.item(0).classList.remove("showing");
        }
        let card = document.getElementById("card");
        let newChat = document.createElement("div");
        newChat.setAttribute("id", "chat:" + destId);
        newChat.classList.add("chat");
        newChat.classList.add("showing");
        newChat.scrollTop = newChat.scrollHeight;

        let divHeader = document.createElement("div");
        divHeader.classList.add("chat-header");
        divHeader.classList.add("clearfix");
        divHeader.classList.add("row");
        divHeader.classList.add("col-lg-6");
        divHeader.classList.add("chat-about");
        divHeader.textContent = destName;

        if(myRole === "tutor") {
            let buttonAdd = document.createElement("button");
            buttonAdd.setAttribute("onclick", "location.href='/addLesson?id=" +destId + "'");
            buttonAdd.textContent = "Add lesson";
            buttonAdd.classList.add("addLessonButton");
            divHeader.appendChild(buttonAdd);
        }

        let divListMessages = document.createElement("div");
        divListMessages.classList.add("chat-history");
        divListMessages.scroll({ top: divListMessages.scrollHeight, behavior: "smooth"});
        let ul = document.createElement("ul");
        ul.classList.add("m-b-0");
        ul.id = "ul:"+destId;

        messages.sort(compareFnc);

        for(let i=0; i<messages.length; i++){
            addMessage(myId, messages[i].idSender, messages[i].timestamp, messages[i].message, ul);
        }

        let externalDivInput = document.createElement("div");
        externalDivInput.classList.add("chat-message");
        externalDivInput.classList.add("clearfix");
        externalDivInput.classList.add("input-group");
        externalDivInput.classList.add("mb-0");

        let internalDivInput = document.createElement("div");
        internalDivInput.classList.add("input-group-prepend");

        let spanInput = document.createElement("span");
        spanInput.classList.add("input-group-text");
        spanInput.setAttribute("onclick", "sendMessage()");

        let imageSend = document.createElement("i");
        imageSend.classList.add("fa");
        imageSend.classList.add("fa-send");

        spanInput.appendChild(imageSend);
        internalDivInput.appendChild(spanInput);

        let inputField = document.createElement("input");
        inputField.setAttribute("type", "text");
        inputField.classList.add("form-control");
        inputField.id = "messageInput:"+destId;
        inputField.setAttribute("placeholder", "Enter text here...");

        inputField.addEventListener("keypress", function(event) {
            // If the user presses the "Enter" key on the keyboard
            if (event.key === "Enter") {
                // Cancel the default action, if needed
                sendMessage();
            }
        });

        externalDivInput.appendChild(internalDivInput);
        externalDivInput.appendChild(inputField);

        divListMessages.appendChild(ul);
        newChat.appendChild(divHeader);
        newChat.appendChild(divListMessages);
        newChat.appendChild(externalDivInput);
        card.appendChild(newChat);
    }
    else if(results[0]=="newMessage"){
        for(let i=1; i<results.length; i++) {
            let fields = results[i].split('"');
            let messageText = fields[1];
            let idSender = fields[3];
            let timestamp = fields[5];
            let nameSender = fields[7];
            let chatDiv = document.getElementById("chat:"+idSender);
            if(chatDiv!=null) {
                //console.log("id sender: ", idSender);
                let ul = document.getElementById("ul:" + idSender);
                addMessage(myId, idSender, timestamp, messageText, ul);
                if(!chatDiv.classList.contains("showing")){
                    addNotification(idSender, nameSender, timestamp, true);
                }else{
                    addNotification(idSender, nameSender, timestamp, false);
                }
            }else{
                addNotification(idSender, nameSender, timestamp, true);
            }
        }
    }
});

function sendMessage(){
    let idDest = document.getElementById("idDest").textContent;
    let messageTextElem = document.getElementById("messageInput:"+idDest);
    let messageText = messageTextElem.value;
    if(messageText!="") {
        let myId = document.getElementById("id").textContent;
        let ul = document.getElementById("ul:" + idDest);
        //console.log("Message input: ", messageText);
        let timestamp = Date.now();
        messageTextElem.value = null;
        addMessage(myId, myId, timestamp.toString(), messageText, ul);
        socket.send(JSON.stringify({
            id: myId,
            myName: document.getElementById("fullname").textContent,
            idDest: idDest,
            role: document.getElementById("role").textContent,
            timestamp: timestamp.toString(),
            message: messageText,
            request: "sendMessage"
        }));
    }
}

function requestMessages(idDest){
    let roleDest = '';
    if(document.getElementById("role").textContent == "student")
        roleDest = "tutor";
    else
        roleDest = "student";
    let active = document.getElementsByClassName("active");
    if(active.length > 0){
        active.item(0).classList.remove("active");
    }
    let li = document.getElementById('idList:'+idDest);
    li.classList.add("active");

    let icon = li.getElementsByClassName("fa-exclamation").item(0);
    let check = icon.classList.contains("showingNotification");

    for(let index=0; index<listOpenChat.length; index++){
        if(listOpenChat[index].id === idDest.toString()) {
            listOpenChat[index].active = true;
            if (check) {
                listOpenChat[index].notification = false;
                icon.classList.remove("showingNotification");
            }
        }else if(listOpenChat[index].active===true){
            listOpenChat[index].active = false;
        }
    }

    document.getElementById("idDest").textContent = idDest;
    document.getElementById("fullnameDest").textContent = document.getElementById('idList:'+idDest).textContent;

    let divChat = document.getElementById("chat:"+idDest);
    let showing = document.getElementsByClassName("showing");
    if(divChat!=null){
        if(showing.length>0)
            showing.item(0).classList.remove("showing");
        divChat.classList.add("showing");
    }else {
        //////////////////////////////////////////
        socket.send(JSON.stringify({
            id: document.getElementById("id").textContent,
            idDest: idDest.toString(),
            role: document.getElementById("role").textContent,
            destRole: roleDest,
            request: "requestMessages"
        }));
    }
}