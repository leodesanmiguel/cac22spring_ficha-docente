// Call the dataTables jQuery plugin
$(document).ready(function() {
cargarUsuarios();
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){
    const request = await fetch('/api/user/listado', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
    });

    const usuarios = await request.json();

    let listadoH = '';
    for (let user of usuarios){
        const userH='<tr><td>'+ user.id +'</td><td>'
                    + user.name + ' ' + user.lastName +'</td><td>'
                    + user.userName +'</td><td>'
                    + user.userLevel +'</td><td>'
                    + user.starDate +'</td><td>'
                    + user.email +'</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
        listadoH += userH;
    }
    document.querySelector('#usuarios tbody').outerHTML=listadoH;
}