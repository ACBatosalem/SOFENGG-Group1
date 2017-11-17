function modalMessage (message, neg, negact, pos, posact) {
    var modalAct = document.createElement('div');
    var modalBg = document.createElement('div');
    var modalCon = document.createElement('div');
    var modalTit = document.createElement('div');
    var buttonYes = document.createElement('button');
    var buttonNo = document.createElement('button');

    $(modalAct).attr('id', 'modal-action');
    $(modalBg).attr('id', 'modal-action-bg');
    $(modalCon).attr('id', 'modal-action-content');
    $(modalTit).attr('id', 'modal-action-label');
    $(modalTit).text(message);
    
    $(modalCon).append(modalTit);
        
    if(neg != null) {       
        $(buttonNo).addClass('modal-action-neg');
        $(buttonNo).text(neg);
        $(buttonNo).click(negact);
        $(modalCon).append(buttonNo);
    }
    
    if(pos != null) {  
        $(buttonYes).addClass('modal-action-pos');
        $(buttonYes).text(pos);
        $(buttonYes).click(posact);
        $(modalCon).append(buttonYes);
    }
    
    $(modalAct).append(modalBg);
    $(modalAct).append(modalCon);

    $('body').append(modalAct);
}