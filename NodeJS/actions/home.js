function home (request, response) {  
    if(request.session.uid == null)
        response.render(path.join(__dirname, './../web/index.ejs'));
    else {
        database.ref('users').child(request.session.uid).once('value', function(snapshot){
            database.ref('orgs').child(snapshot.val().orgUID).once('value', function(snapshot){
                if(snapshot.val().priveleges.toUpperCase() == "ADMIN") {
                    response.redirect(context+'/aps');
                } else {
                    response.redirect(context+'/org');
                }
                return true;
            });
            return true;
        });
    }
}