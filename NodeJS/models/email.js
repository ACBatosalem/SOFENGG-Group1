var nodemailer = require('nodemailer');
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

var transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'jonal_ticug@dlsu.edu.ph',
        pass: 'Ayo5Get5??'
    }
});

var mailOptions = {
  from: 'jonal_ticug@dlsu.edu.ph',
  to: 'sophia_rivera@dlsu.edu.ph',
  subject: 'Sending Email using Node.js',
  text: 'That was easy!'
};

transporter.sendMail(mailOptions, function(error, info){
    if (error) {
        console.log(error);
    } else {
        console.log('Email sent: ' + info.response);
    }
});