var express = require('express');
var router = express.Router();
var md5 = require('md5');
var axios = require('axios');
const {
  response
} = require('express');
const {
  header
} = require('express/lib/request');
var coki;




/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('login', {
    codigoAcceso: 0
  });
});

router.get('/VPF/sesionExpirada', function (req, res, next) {
  res.render('login', {
    codigoAcceso: 4
  });
});


router.get('/VPF/*', function (req, res, next) {
  res.render('login', {
    codigoAcceso: 3
  });
});


router.post('/VPF/logueo', function (req, res, next) {
  let usuario = {
    usuario: req.body.userName,
    password: md5(req.body.password)
  }

  if (usuario.usuario == "" || usuario.password == "") {

    res.render("login", {
      codigoAcceso: 2
    })
  } else {

    axios({
        method: "post",
        url: "http://localhost:8888/loginUser",
        data: usuario
      }).then(response => {

        user = response.data;

        if (user != '') {

          axios({
            method: "GET",
            url: "http://localhost:8888/API/mascota_por_usuario/" + parseInt(user[0]),
            headers: {
              "Authorization": user[1] + ""
            }
          }).then(response => {

            usuario = response.data;

            res.cookie("token", user[1]);
            res.render("ListaMisMascotas", {
              usuario: usuario,
              token: user[1]
            })
          }).catch(error => {
            console.log(error);
            res.render("construccion");
          });
        } else {

          res.render('login', {
            codigoAcceso: 1
          })
        }


      })
      .catch(error => {
        console.log(error);
        res.render('construccion')
      })

  }




});

// Ver masciota del usuario
router.post('/VPF/verUnaMascota', function (req, res, next) {
  console.log("Buscando mascota")
  let IdMascota = req.body.IdMascota;
  let IdUsuario = req.body.IdUsuario;
  let token = req.body.token;

  console.log(IdMascota)
  console.log(token)
  axios({
      method: "GET",
      url: "http://localhost:8888/API/mascota_por_id/" + IdMascota,
      headers: {
        Authorization: token
      }
    })
    .then((response) => {
      let mascota;
      mascota = response.data;
      console.log(mascota);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: IdUsuario,
        token: token
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });

});

router.post('/VPF/listaCitasMascotas', function (req, res, next) {
  res.render('listaCitasMascotas');
});


router.post('/VPF/guardarMascota', function (req, res, next) {
  console.log("Guardando mascota");
  let token = req.body.token;
  let mascota = {
    idMascota: req.body.IdMascota,
    idUsuario: req.body.IdUsuario,
    tipo: req.body.tipoMascota,
    edad: req.body.edadMascota,
    nombreMascota: req.body.nombreMascota
  };
  console.log(mascota);
  axios({
      method: "GET",
      url: "http://localhost:8888/API/guardar_mascota",
      data: mascota,
      headers: {
        Authorization: token
      }
    })
    .then((response) => {
      console.log("Se guardo la mascota");
      mascota = response.data;
      console.log(mascota);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: mascota.idUsuario,
        token: token
      });
    })
    .catch((error) => {
      console.log(error);
      console.log("No se guardo la mascota");
      res.render('construccion');
    });
});

router.post('/VPF/listaMascotas', function (req, res, next) {
  console.log("Buscando las mascotas del usuario");
  let IdUsuario = req.body.IdUsuario;
  let token = req.body.token;
  console.log(token)

  axios({
    method: "GET",
    url: "http://127.0.0.1:8888/API/mascota_por_usuario/" + IdUsuario,
    headers: {
      Authorization: token 
    }
  }).then(response => {
    let usuario = response.data;    
    console.log(usuario);
    res.render("ListaMisMascotas", {
      usuario: usuario,
      token: token
    })
  }).catch(error => {
    console.log(error);
    console.log(token);
    res.render("construccion");
  });

});


router.post('/VPF/nuevaMascota', function (req, res, next) {
  console.log("Nueva mascota");
  let token = req.body.token;
  let mascota = {
    idMascota: 0,
    idUsuario: req.body.IdUsuario,
    tipo: '',
    edad: '',
    nombreMascota: ''
  };
  console.log(mascota);
  res.render('verUnaMascota', {
    mascota: mascota,
    IdUsuario: mascota.idUsuario,
    token: token
  });
});

router.post('/VPF/eliminarMascota', function (req, res, next) {
  let token = req.body.token;
  let mascota = {
    idMascota: req.body.IdMascota,
    idUsuario: req.body.IdUsuario,
    tipo: '',
    edad: '',
    nombreMascota: ''
  };
  axios({
      method: "GET",
      url: "http://localhost:8888/API/eliminar_mascota/" + mascota.idMascota,
      headers: {
        Authorization: token
      }
    })
    .then((response) => {

      let respuesta = response.data;
      console.log(respuesta);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: mascota.idUsuario,
        respuesta: respuesta,
        token:token
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });

});

router.get('/nuevoUsuario', function (req, res, next) {
  console.log("Nuevo usuario");
  res.render('nuevoUsuario');
});



router.post('/VPF/crearUsuario', function (req, res, next) {
  let token = req.body.token;
  let usuario = {
    idUsuario: req.body.IdUsuario,
    nombrePersonal: req.body.nombrePersonal,
    userName: req.body.userName,
    password: md5(req.body.password),
    tipoUsuario: "Cliente",
    edad: req.body.edad,
    telefono: req.body.telefono,
    direccion: req.body.direccion
  }
  console.log(usuario);


  axios({
    method : "POST",
    url: "http://127.0.0.1:8888/API/nuevo_usuario",
    data : usuario,
    headers: {
      Authorization : token
    }
  })
    .then((response) => {
      let respuesta = response.data;
      console.log(respuesta);
      if (usuario.idUsuario == 0) {
        res.render('login');
      } else {
        axios({
          method: "GET",
          url : "http://127.0.0.1:8888/API/usuario_por_id/" + usuario.idUsuario,
          headers: {
            Authorization : token
          }
        })
          .then((response) => {

            let usuario = response.data;
            console.log(usuario);
            res.render('miPerfil', {
              usuario: usuario,
              token : token
            });
          })
          .catch((error) => {
            console.log(error);
            res.render('construccion');
          });
      }

    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });


});



router.post('/VPF/miPerfil', function (req, res, next) {

  let IdUsuario = req.body.IdUsuario
  let token = req.body.token
  console.log(IdUsuario);

  axios({
    method : "GET",
    url : "http://127.0.0.1:8888/API/usuario_por_id/" + IdUsuario,
    headers: {
      Authorization : token
    }
  })
    .then((response) => {

      let usuario = response.data;
      console.log(usuario);
      res.render('miPerfil', {
        usuario: usuario,
        token:token
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });


});

module.exports = router;

/*
router.post('/', function (req, res, next) {
  res.render('');
});
*/