let calendar = document.querySelector("div:nth-of-type(3)")
let date = new Date();
let dateAWeekLater = new Date(date.getFullYear(), date.getMonth() + 1, date.getDate() + 7);
let days = calendar.querySelectorAll("div")
let tasks = [];
let taskTags = document.querySelectorAll(".taskdesc")
let dateTags = document.querySelectorAll(".dates")
let editLinkTags = document.querySelectorAll(".editLinks")
let dueDates = []
document.querySelectorAll(".dates").forEach(spanTag => {
    dueDates.push(spanTag.innerHTML);
    var dateArray = spanTag.innerHTML.split(''); 
    var lastChar = parseInt(dateArray[dateArray.length - 1]);
    lastChar++;
    dateArray[dateArray.length - 1] = lastChar.toString();
    var updatedDateString = dateArray.join('');
    let checkingDate = new Date(updatedDateString);
    if (checkingDate < date) {
        spanTag.style.color = "red";
    }
})
for (let i = 0; i < taskTags.length; i++) {
    tasks.push(taskTags[i].innerHTML);
    var dateArray = dateTags[i].innerHTML.split(''); 
    var lastChar = parseInt(dateArray[dateArray.length - 1]);
    lastChar++;
    dateArray[dateArray.length - 1] = lastChar.toString();
    var updatedDateString = dateArray.join('');
    let checkingDate = new Date(updatedDateString);
    if (checkingDate < date) {
        taskTags[i].style.color = "red";
        editLinkTags[i].innerHTML = "Reschedule";
    }
}
let taskIds = []
document.querySelectorAll(".editLinks").forEach(linkTag => {
    taskIds.push(linkTag.getAttribute("href").slice(-1));
})
for (let i = 0; i < days.length; i++) {
    let dateForDiv = new Date(date.getFullYear(), date.getMonth(), date.getDate() + i);
    days[i].innerHTML = "<p>" + dateForDiv.getDate() + "-" + (dateForDiv.getMonth() + 1) + "-" + dateForDiv.getFullYear() + "</p>";
}
for (let calendarIndex = 0; calendarIndex < days.length; calendarIndex++) {
    let dateForDiv = new Date(date.getFullYear(), date.getMonth(), (date.getDate() + calendarIndex));
    for (let dueDateIndex = 0; dueDateIndex < dueDates.length; dueDateIndex++) {
        if ((dateForDiv.getFullYear() + "-" + (dateForDiv.getMonth() + 1) + "-" + dateForDiv.getDate()) == dueDates[dueDateIndex]) {
            days[calendarIndex].innerHTML += "<hr><p>" + tasks[dueDateIndex] + "</p>";
        }
    }
}
function filterTasks() {
    let taskFilter = document.querySelector("div:nth-of-type(1) form input:checked").value;
    let dateColumns = document.querySelectorAll(".dates")
    if (taskFilter == "todaysTasks") {
        document.querySelector("div:nth-of-type(1) h2").innerHTML = "Tasks for Today";
        let tableBody = document.querySelector("div:nth-of-type(2) table tbody");
        tableBody.innerHTML = "";
        for (let i = 0; i < tasks.length; i++) {
            if (dueDates[i] == date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()) {
                tableBody.innerHTML += '<tr><td><span class="taskdesc">' + tasks[i] + '</span></td><td><span class="dates">' + dueDates[i] + '</span></td><td><a href="/sandnavl/edit/' + taskIds[i] + '">Edit</a></td></tr>'
            }
        }
    }
}
let lastCheckedButton = document.querySelector("div:nth-of-type(1) form input[type=hidden]").value;
document.querySelectorAll("div:nth-of-type(1) form input[type=radio]").forEach(button => {
    if (button.value == lastCheckedButton) {
        button.checked = "true";
    }
})
filterTasks()
