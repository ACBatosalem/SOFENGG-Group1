// USED MODULES
var path        = require("path");
var session     = require("express-session");
var nodemailer = require('nodemailer');

process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

// PERSONAL MODULES
var firebase    = require("./../models/firebase");
var utils       = require("./../utils/utils");
var emailService= require("./../models/email");

// VARIABLES
var execute     = { };
var context     = "/APS_Dashboard";

var numOrgs = 0;
var numSub = 0;
var service = firebase.service;

// ACTION HANDLER MAP
execute[context+"/home"] = home;
execute[context+"/login"] = login;
execute[context+"/logout"] = logout;
execute[context+"/forgotPassword"] = forgotPassword;
execute[context+"/modalData"] = modalData;
execute[context+"/checkSubmission"] = checkSubmission;

execute[context+"/org"] = home_org;
execute[context+"/org/profile"] = profileOrg;
execute[context+"/org/statistics"] = statisticsOrg;
execute[context+"/org/newSubmission"] = newSubmission;
execute[context+"/org/submitSubmission"] = submitSubmission;
execute[context+"/resubmitSubmission"] = resubmitSubmission;
execute[context+"/org/editUser"] = editUser;
execute[context+"/org/changePassword"] = changePassword;

execute[context+"/aps"] = home_aps;
execute[context+"/aps/filterSubmissions"] = filterSubmissions;
execute[context+"/deleteSubmission"] = deleteSubmission;
execute[context+"/aps/profile"] = profileAPS;
execute[context+"/aps/profile/"] = profileAPS;
execute[context+"/aps/statistics"] = statisticsAPS;
execute[context+"/aps/accounts"] = accountsAPS;
execute[context+"/aps/addUser"] = addUser;
execute[context+"/aps/editUser"] = editUser;
execute[context+"/aps/deleteUser"] = deleteUser;
execute[context+"/aps/addOrganization"] = addOrganization;
execute[context+"/aps/changeStatus"] = changeStatus;
execute[context+"/aps/changePassword"] = changePassword;
execute[context+"/aps/newSubmission"] = apsNewSubmission;
execute[context+"/aps/submitSubmission"] = submitSubmission;

//execute[context+"/addOrg"] = addOrg;

firebase.initialize();

function accountsAPS (request, response) {
    if(request.session.uid == null) {
        response.redirect(context+'/home');
    } else {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/aps_organizations.ejs"), {
            user:user,
            context:context,
            organizations: service.getAllOrganizationsWithUsers(),
            users: service.getAllUsersWithOrganizations(),
            message: ""
        });
    }
}

function statisticsAPS (request, response) {
    if(request.session.uid == null) {
        response.redirect(context+'/home');
    } else {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/aps_statistics.ejs"), {
            user:user,
            context:context,
            academic: service.countAcademic(),
            nonacademic: service.countNonacademic()
        });
    }
}

function statisticsOrg (request, response) {
    if(request.session.uid == null) {
        response.redirect(context+'/home');
    } else {
        var user = service.getUserWithOrganization(request.session.uid);
        console.log(user.org_id);
        response.render(path.join(__dirname, "./../web/orgs_statistics.ejs"), {
            user:user,
            context:context,
            academic: service.countAcademic(user.org_id),
            nonacademic: service.countNonacademic(user.org_id)
        });
    }
}

// ACTION HANDLERS
function profileOrg (request, response) {
    if(request.session.uid == null) {
        response.redirect(context+'/home');
    } else {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/orgs_profile.ejs"), {
            user:user,
            context:context
        });
    }
}

function profileAPS (request, response) {
    if(request.session.uid == null) {
        response.redirect(context+'/home');
    } else {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/aps_profile.ejs"), {
            user:user,
            context:context
        })
    }
}

function home (request, response) {  
    if(request.session.uid == null)
        response.render(path.join(__dirname, "./../web/index.ejs"), {
            context:context
        });
    else {
        var user = service.getUserWithOrganization(request.session.uid);
        if(user.org.privilege == "ADMIN") {
            response.redirect(context+"/aps");
        } else {
            response.redirect(context+"/org");
        }
    }
}

function login (request, response) {     
    if(request.session.uid == null) { 
        var username = request.body.username;  
        var password = request.body.password;

        if(username == "" || username == undefined || password == "" || password == undefined)
            response.redirect(context+"/home");
        else {        
            var loginDetails = service.loginUser(username, password);
            if(loginDetails.test == false) { 
                response.send("Username does not exist!");
            } else if (loginDetails.test == true && loginDetails.user == null) {
                response.send("Password incorrect!");
            } else if (loginDetails.test == true && loginDetails.user != null) {
                request.session.uid = loginDetails.user.user_id;
                request.session.org_id = loginDetails.user.org.org_id;
                if(loginDetails.user.org.privilege.toUpperCase() == "ADMIN") {
                    response.send("aps");
                } else {
                    response.send("org");
                }
            }
        }   
    } 
}

function logout (request, response) {
    request.session.uid = null;
    response.redirect(context+ "/home");
}

function home_aps (request, response) {
    if(request.session.uid == null)
        response.redirect(context + "/home");
    else {
        var user = service.getUserWithOrganization(request.session.uid);
        if(user.org.privilege.toUpperCase() == "ADMIN") {
            response.render(path.join(__dirname, "./../web/aps_submissions.ejs"), {
                    user: user,
                    context: context,
                    organizations: service.getAllOrganizations(), 
                    submissions: service.getAllCompleteSubmissions()});
        } else {
            response.redirect(context+"/org");
        }
    }
}

function home_org (request, response) {
    if(request.session.uid == null)
        response.redirect(context + "/home");
    else {
        var user = service.getUserWithOrganization(request.session.uid);
        if(user.org.privilege.toUpperCase() != "ADMIN") {
            response.render(path.join(__dirname, "./../web/orgs_submissions.ejs"), {
                    user: user,
                    context: context,
                    submissions: service.getCompleteSubmissionsByOrg(user.org_id)});
        } else {
            response.redirect(context+"/aps");
        }
    }
}

function modalData(request, response) {
    var key = request.body.docuID;
    var orgID = request.session.org_id;
    var checker = service.getUser(request.session.uid).name;
    var date = new Date();
    var datetimeNow = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString().substring(0,19);
    response.setHeader('Content-Type', 'application/json');
    response.send({sub: service.getCompleteSubmission(key), 
                   org_id: orgID, checkerName: checker, now: datetimeNow});
}

function generatePassword (n) {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for (var i = 0; i < 10; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function addUser (request, response) {
    if(request.session.uid != null) { 
        var user_name = request.body.name;
        var user_username = request.body.username;
        var user_email = request.body.email;
        var user_contact = request.body.contact;
        var user_org = request.body.org;
        
        var randomPass = generatePassword(10);
        
        
        if(service.findUser(user_name,user_username,user_contact,user_email)) {
            response.send("Name, username, contact number, or email already in use.");
        } else {
            var userDetails = 
            {   name: user_name,
                username: user_username,
                email: user_email,
                contact: user_contact,
                org_id: user_org,
                password: randomPass
            };
            
            emailService.sendMail({
                from : "dlsucso.apsdashboard@gmail.com",
                to : user_email,
                subject: "[APS Dashboard] Account Setup",
                text: "Hello and thank you for using the APS Dashboard. An account was generated using this email account. " +
                    "You may login the credentials below at https://csoaps.localtunnel.me \n" + 
                    "Username: " + user_username + " \nPassword: " + randomPass
            });
            service.addUser(userDetails);
            response.send("true");
            }
        } else {
            response.send("false");
        }
}
function addOrganization(request,response) {
    if(request.session.uid != null) { 
        var org_name = request.body.name;  
        var org_username = request.body.username;

        if(service.findOrg(org_name,org_username)) {
            response.send("Organization name or username already in use.");
        } else {
            numOrgs = service.countOrgs() + 1;
            var orgKey = 'org_'+numOrgs;
            var orgDetails = { 
                name: org_name,
                username: org_username,
                status: "active",
                privilege: "org"
            };
            
            service.addOrganization(orgKey, orgDetails);
            response.send("true");
        }
        
    } else {
        response.send("false");
    }
}

function changeStatus(request,response) {

    if(request.session.uid != null) {
        //TODO replace child with id of org from request
        service.changeOrgStatus(request.body.id, request.body.status);
        var user = service.getUserWithOrganization(request.session.uid);
        response.send('true');
    } else {
        response.send('false');
    }
}

function deleteUser(request,response) {
    if(request.session.uid != null) {
        //TODO replace child with id of org from request
        service.deleteUser(request.body.id);
        var user = service.getUserWithOrganization(request.session.uid);
        response.send('true');
    } else {
        response.send('false');
    }
}

function newSubmission(request, response) {
    if(request.session.uid != null) {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/orgs_submission_form.ejs"), {
              user: user,
              context: context
        });
    } else {
        response.redirect(context+ '/home');
    }
}

function apsNewSubmission(request, response) {
    if(request.session.uid != null) {
        var user = service.getUserWithOrganization(request.session.uid);
        response.render(path.join(__dirname, "./../web/aps_submission_form.ejs"), {
              user: user,
              context: context,
              organizations: service.getAllOrganizations()
        });
    } else {
        response.redirect(context+ '/home');
    }
}

function submitSubmission(request, response) {
    if(request.session.uid != null) {
        console.log("im here na");
        var term = request.body.term;
        var type_sub = request.body.type_sub;
        var type_sas = request.body.type_sas;
        var act_title = request.body.act_title;
        var act_nature = request.body.act_nature;
        var act_type = request.body.act_type;
        var act_date = request.body.act_date;
        var act_time = request.body.act_time;
        var act_venue = request.body.act_venue;
        var submitter;
        
        if (request.session.org_id != "org_1")
            submitter = request.session.org_id;
        else {
            console.log(request.body.org);
            submitter = request.body.org;
        }

        console.log(term + " "+type_sub+" " + " " + type_sas + " "+act_title);
        console.log(act_nature +" "+ act_type + " " + act_date + " "+act_venue);
        console.log(submitter);
        if (term == "" || term == undefined ||
            act_title == "" || act_title == undefined ||
            act_nature == "" || act_nature == undefined ||
            act_type == "" || act_type == undefined ||
            act_date == "" || act_date == undefined ||
            act_time == "" || act_time == undefined ||
            act_venue == "" || act_venue == undefined) {
                response.send({msg:false,error:"missing"});
        } else {
            numSub = service.countSubs() + 1;
            var subKey = 'sub_'+numSub;
            var date = new Date();
            var d = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString().split('T');

            var subDetails = { 
                act_date: act_date,
                act_nature: act_nature,
                act_time: act_time,
                act_type: act_type,
                act_venue: act_venue,
                sub_type: type_sub,
                term: term,
                timestamp: d[0] + ' ' + d[1].substring(0,8),
                act_title: act_title,
                type_sas: type_sas,
                user_id_org: request.session.uid,
                org_id: submitter,
                user_id_checker: "-",
                datetimechecked: "-",
                status: "-"
            };
            
            service.addSubmission(subKey, subDetails);
            response.send({msg:true});
        }
    } else {
        response.send({msg:false});
    }
}

function resubmitSubmission(request, response) {
    if(request.session.uid != null) {
        console.log("im here na");
        var term = request.body.term;
        var type_sub = request.body.type_sub;
        var type_sas = request.body.type_sas;
        var act_title = request.body.act_title;
        var act_nature = request.body.act_nature;
        var act_type = request.body.act_type;
        var act_date = request.body.act_date;
        var act_time = request.body.act_time;
        var act_venue = request.body.act_venue;
        var submitter = service.findOrgByName(request.body.org).org_id;
        
        

        console.log(term + " "+type_sub+" " + " " + type_sas + " "+act_title);
        console.log(act_nature +" "+ act_type + " " + act_date + " "+act_venue);
        console.log(submitter);
        if (term == "" || term == undefined ||
            act_title == "" || act_title == undefined ||
            act_nature == "" || act_nature == undefined ||
            act_type == "" || act_type == undefined ||
            act_date == "" || act_date == undefined ||
            act_time == "" || act_time == undefined ||
            act_venue == "" || act_venue == undefined) {
                response.send({msg:false,error:"missing"});
        } else {
            numSub = service.countSubs() + 1;
            var subKey = 'sub_'+numSub;
            var date = new Date();
            var d = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString().split('T');
            var subDetails = { 
                act_date: act_date,
                act_nature: act_nature,
                act_time: act_time,
                act_type: act_type,
                act_venue: act_venue,
                sub_type: type_sub,
                term: term,
                timestamp: d[0] + ' ' + d[1].substring(0,8),
                act_title: act_title,
                type_sas: type_sas,
                user_id_org: request.session.uid,
                org_id: submitter,
                user_id_checker: "-",
                datetimechecked: "-",
                status: "-"
            };
            
            service.addSubmission(subKey, subDetails);
            response.send({msg:true});
        }
    } else {
        response.send({msg:false});
    }
}

function editUser(request, response) {
    if(request.session.uid != null) {
        var name = request.body.name;
        var email = request.body.email;
        var contact = request.body.contact;
        var username = request.body.username;

        if(service.findUserExist(name,username,contact,email,request.session.uid)) {
            response.send("Name, username, contact number, or email already in use.");
        } else {
            var userDetails = {
                name: name,
                email: email,
                contact: contact,
                username: username
            };

            service.editUser(request.session.uid, userDetails);
            response.send('true');
        }
        
    } else {
        response.send('false');
    }
}

function changePassword(request, response) {
    if(request.session.uid != null) {
        var oldPass = request.body.oldpass;
        var newPass = request.body.newpass;
        var cNewPass = request.body.cnewpass;

        console.log("HELLO??");

        var user = service.getUser(request.session.uid);

        if (oldPass != user.password) {
            console.log("Incorrect input for current password.");
            response.send("Incorrect input for current password.");
        } else {
            service.changePassword(request.session.uid, newPass);
            console.log("Password successfully changed!");
            response.send("Password successfully changed!");
        }
    } else {
        response.send('false');
    }
}

function filterSubmissions(request, response) {

    if (request.session.uid != null) {
        var filter = request.body.filter;
        var orgID = request.body.orgID;

        var all = service.getAllCompleteSubmissions();
        var subs = [];
        for (var sub in all) {
            if ((orgID == "0" || all[sub].org_id == orgID) && 
                (filter == "all" ||
                filter == "acad" && all[sub].act_nature == "Academic"  ||
                filter == "non-acad" && all[sub].act_nature != "Academic")) {
                subs.push(all[sub]);
            }
        }

        response.send(JSON.stringify(subs));

    } else {
        response.send({subs:'false'});
    }
}

function forgotPassword(request, response){
    var user = service.findUserByEmail(request.body.email);

    if(user != false) {

        emailService.sendMail({
            from : "dlsucso.apsdashboard@gmail.com",
            to : request.body.email,
            subject: "[APS Dashboard] Forgot Password",
            text: "Hello and thank you for using the APS Dashboard. \n" + 'Your password is: ' + user.password
        });
        response.send('Email sent!');
        
    } else {
        response.send("Invalid Email");
    }
}

function checkSubmission(request, response) {
    if (request.session.uid != null) {
        var checkDetails = {
            checker: request.session.uid,
            remarks: request.body.remarks,
            status: request.body.status
        };
        service.checkSubmission(request.body.subKey, checkDetails);
        response.send({msg: 'true'});
    } else {
        response.send({msg: 'false'});
    }
}

function deleteSubmission(request, response) {
    if (request.session.uid != null) {
        service.deleteSubmission(request.body.subKey);
        response.send({msg: 'true'});
    } else {
        response.send({msg: 'false'});
    }
}

exports.execute = execute;