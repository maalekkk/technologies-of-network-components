function minDate(input) {
    const today = new Date();
    var day = today.getDate();
    var month = today.getMonth() + 1;
    var year = today.getFullYear();
    var hour = today.getHours();
    var minutes = today.getMinutes();
    if (day < 10)
        day = '0' + day
    if (month < 10)
        month = '0' + month
    if (hour < 10)
        hour = '0' + hour;
    if (minutes < 10)
        minutes = '0' + minutes;
    input.min = year + '-' + month + '-' + day + 'T' + hour + ':' + minutes;
}

