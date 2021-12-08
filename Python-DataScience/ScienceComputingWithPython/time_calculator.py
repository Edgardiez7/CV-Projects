def add_time(start, duration, day=""):
    weekDays = ["Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"]
    # parse time to add
    add = duration.split(":")
    add_hours = int(add[0])
    add_mins = int(add[1])
    days_later = 0

    # parse to 24h format
    startArray = start.split()
    pm = startArray[1]
    start24 = startArray[0].split(":")
    hours = int(start24[0])
    mins = int(start24[1])
    if(pm == "AM"):
        if(hours == 12):
            hours -= 12
    if(pm == "PM"):
        if(hours >= 1 and hours <= 11):
            hours += 12
    # add minutes
    if((mins + add_mins) < 60):
        mins += add_mins
    else:
        hours += 1
        if(hours == 24):
            hours = 0
            days_later += 1
        mins = (mins+add_mins)-60
    # add hours
    for i in range(add_hours):
        hours += 1
        if(hours == 24):
            hours = 0
            days_later += 1
    # parse to pm format
    if(hours == 0):
        hours += 12
        pm = "AM"
    elif(hours >= 1 and hours <= 11):
        pm = "AM"
    elif(hours >= 13 and hours <= 23):
        hours -= 12
        pm = "PM"
    elif(hours == 12):
        pm = "PM"
    # add 0 to the left if 1 digit minutes
    if(mins <= 9):
        mins = "0" + str(mins)
    time_output = str(hours) + ":" + str(mins) + " " + pm
    # if day in arg
    if(day):
        day = day.lower()
        day = day.capitalize()
        start_index = weekDays.index(day)
        for i in range(days_later):
            start_index += 1
            if(start_index == 7):
                start_index = 0
        time_output += ", " + weekDays[start_index]

    # add days later
    if(days_later > 0):
        if(days_later == 1):
            time_output += " (next day)"
        else:
            time_output += " (" + str(days_later) + " days later)"

    print(time_output)


add_time("8:16 PM", "466:02", "tuesday")
