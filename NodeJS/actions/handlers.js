// USED MODULES
var path        = require("path");
var session     = require("express-session");

// PERSONAL MODULES
var firebase    = require("./../models/firebase");
var utils       = require("./../utils/utils");

// VARIABLES
var execute     = { };
var context     = "/APS_Dashboard";

var numOrgs = 0;
var numSub = 0;
var service    = firebase.service;

// ACTION HANDLER MAP
execute[context+"/home"] = home;
execute[context+"/login"] = login;
execute[context+"/logout"] = logout;
execute[context+"/modalData"] = modalData;

execute[context+"/org"] = home_org;
execute[context+"/org/profile"] = profileOrg;
execute[context+"/org/statistics"] = statisticsOrg;
execute[context+"/org/newSubmission"] = newSubmission;
execute[context+"/org/submitSubmission"] = submitSubmission;
execute[context+"/org/editUser"] = editUser;

execute[context+"/aps"] = home_aps;
execute[context+"/aps/profile"] = profileAPS;
execute[context+"/aps/profile/"] = profileAPS;
execute[context+"/aps/statistics"] = statisticsAPS;
execute[context+"/aps/accounts"] = accountsAPS;
execute[context+"/aps/addUser"] = addUser;
execute[context+"/aps/deleteUser"] = deleteUser;
execute[context+"/aps/addOrganization"] = addOrganization;
execute[context+"/aps/changeStatus"] = changeStatus;
execute[context+"/aps/editUser"] = editUser;

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
            users: service.getAllUsersWithOrganizations()
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
                    orgs: service.getAllOrganizations(), 
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
    response.setHeader('Content-Type', 'application/json');
    response.send(service.getCompleteSubmission(key));
}

function addUser (request, response) {
    if(request.session.uid != null) { 
        var user_name = request.body.name;
        var user_username = request.body.username;
        var user_email = request.body.email;
        var user_contact = request.body.contact;
        var user_org = request.body.org;
        
        if(user_username == "" || user_username == undefined ||
            user_name == "" || user_name == undefined ||
            user_email == "" || user_email == undefined ||
            user_contact == "" || user_contact == undefined ||
            user_org == "" || user_org == undefined  ) {
            response.redirect(context+ '/aps/accounts');
        } else {
            var userDetails = 
            {   name: user_name,
                username: user_username,
                email: user_email,
                contact: user_contact,
                org_id: user_org,
                password: "password"
            };
            service.addUser(userDetails);
            response.redirect(context+ '/aps/accounts');
        }
    }
}
function addOrganization(request,response) {
    if(request.session.uid != null) { 
        var org_name = request.body.name;  
        var org_username = request.body.username;

        if(org_username == "" || org_username == undefined || 
            org_name == "" || org_name == undefined) {
            response.redirect(context+ '/aps/accounts');
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
            response.redirect(context+ '/aps/accounts');
        }
    } else {
        response.redirect(context+ '/aps/accounts');
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

function submitSubmission(request, response) {
    if(request.session.uid != null) {
        var term = request.body.term;
        var type_sub = request.body.type_sub;
        var type_sas = request.body.type_sas;
        var act_title = request.body.act_title;
        var act_nature = request.body.act_nature;
        var act_type = request.body.act_type;
        var act_date = request.body.act_date
        var act_time = request.body.act_time;
        var act_venue = request.body.act_venue;
        var submitter;
        
        if (term == "" || term == undefined ||
            act_title == "" || act_title == undefined ||
            act_nature == "" || act_nature == undefined ||
            act_type == "" || act_type == undefined ||
            act_date == "" || act_date == undefined ||
            act_time == "" || act_time == undefined ||
            act_venue == "" || act_venue == undefined) {
                response.redirect(context+'/org/newSubmission');
        } else {
            numSub = service.countSubs() + 1;
            var subKey = 'sub_'+numSub;
            var subDetails = { act_date: act_date,
                act_nature: act_nature,
                act_time: act_time,
                act_type: act_type,
                act_venue: act_venue,
                sub_type: type_sub,
                term: term,
                timestamp: utils.toUTC(new Date()),
                act_title: act_title,
                type_sas: type_sas,
                user_id_org: request.session.uid
            };
            
            response.redirect(context+'/home');
        }
    } else {
        response.redirect(context+ '/home');
    }
}

function editUser(request, response) {
    if(request.session.uid != null) {
        var name = request.body.name;
        var email = request.body.email;
        var contact = request.body.contact;
        var username = request.body.username;

        if(username == "" || username == undefined ||
            name == "" || name == undefined ||
            email == "" || email == undefined ||
            contact == "" || contact == undefined) {
            response.send('Please fill out all fields');
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
        response.redirect(context+ '/home');
        response.send('false');
    }
}

exports.execute = execute;