from tabulate import tabulate 

headers = [
    "EndPoint",
    "Method",
    "Parameters",
    "Return",
    "Description"
]

data = [
    [ "/register", "POST", "email: `String`", "username: `String`, password: `String`", "", "" ],
    [ "/login", 'POST', 'email: `String`', 'password: `String`', '', ''],
    [ "/followers", 'GET', 'token: `String`', "", ""],
    [ "/followed", 'GET', 'token: `String`', '', '' ],
    [ "/follow", 'GET', 'token: `String`, toUserName: `String`', '', '' ],
    [ "/mute", 'POST', 'token: `String`, toUserName: `String`'],
    [ "/updateProfile" ],
    [ "/updatePassword",   ],
    [ "/profile", "GET", "userName: `String`", "", ""]
    [ "/searchUser", "GET", "userName: `String`", "", ""]
]
table = tabulate(data, headers=headers, tablefmt="pipe")
print(table)