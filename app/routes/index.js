var express = require('express');
var router = express.Router();
var axios = require('axios');



/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('login', {
    codigoAcceso: 0
  });
});



router.post('/logueo', function (req, res, next) {
  let usuario = {
    userName: req.body.userName,
    password: req.body.password
  }

  if (usuario.userName == "" || usuario.password == "") {
    console.log("Campos imcompletos");
    res.render("login", {
      codigoAcceso: 2
    })
  } else {
    let url = "http://localhost:8888/logeo/" + usuario.userName + '/' + usuario.password;
    console.log(url)
    axios.post(url)
      .then(response => {

        usuario = response.data;
        //console.log(usuario);
        if (usuario.idUsuario != 0) {
          axios.get("http://localhost:7777/mascota_por_usuario/" + usuario.idUsuario)
            .then((response) => {

              usuario = response.data;
              console.log(usuario);
              res.render("ListaMisMascotas", {
                usuario: usuario
              })
            })
            .catch((error) => {
              console.log(error);
              res.render('construccion');
            });


        } else {
          console.log("Acceso denegado");
          res.render('login', {
            codigoAcceso: 1
          });
        }
      })
      .catch(error => {
        console.log(error);
        res.render('construccion');
      });
  }




});

// Ver masciota del usuario
router.post('/verUnaMascota', function (req, res, next) {
  console.log("Buscando mascota")
  let IdMascota = req.body.IdMascota;
  let IdUsuario = req.body.IdUsuario;
  let mascota;
  console.log(IdMascota)
  axios.get("http://localhost:7777/mascota_por_Id/" + IdMascota)
    .then((response) => {

      mascota = response.data;
      console.log(mascota);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: IdUsuario
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });

});

router.post('/listaCitasMascotas', function (req, res, next) {
  res.render('listaCitasMascotas');
});


router.post('/guardarMascota', function (req, res, next) {

  let mascota = {
    idMascota: req.body.IdMascota,
    idUsuario: req.body.IdUsuario,
    tipo: req.body.tipoMascota,
    edad: req.body.edadMascota,
    nombreMascota: req.body.nombreMascota
  };
  console.log(mascota);
  axios.post("http://localhost:7777/guardar_mascota", mascota)
    .then((response) => {

      mascota = response.data;
      console.log(mascota);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: mascota.idUsuario
      });
    })
    .catch((error) => {
      //console.log(error);
      res.render('construccion');
    });
});





router.post('/listaMascotas', function (req, res, next) {
  let IdUsuario = req.body.IdUsuario
  axios.get("http://localhost:7777/mascota_por_usuario/" + IdUsuario)
    .then((response) => {

      let usuario = response.data;
      console.log(usuario);
      res.render("ListaMisMascotas", {
        usuario: usuario
      })
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });

});


router.post('/nuevaMascota', function (req, res, next) {
  console.log("Nueva mascota");
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
    IdUsuario: mascota.idUsuario
  });
});

router.post('/eliminarMascota', function (req, res, next) {
  let mascota = {
    idMascota: req.body.IdMascota,
    idUsuario: req.body.IdUsuario,
    tipo: '',
    edad: '',
    nombreMascota: ''
  };
  axios.post("http://localhost:7777/eliminar_mascota/" + mascota.idMascota)
    .then((response) => {

      let respuesta = response.data;
      console.log(respuesta);
      res.render('verUnaMascota', {
        mascota: mascota,
        IdUsuario: mascota.idUsuario,
        respuesta: respuesta
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });

});

router.get('/nuevoUsuario', function (req, res, next) {
  res.render('nuevoUsuario');
});

module.exports = router;

router.post('/crearUsuario', function (req, res, next) {

  let usuario = {
    idUsuario: req.body.IdUsuario,
    nombrePersonal: req.body.nombrePersonal,
    userName: req.body.userName,
    password: req.body.password,
    tipoUsuario: "Cliente",
    edad: req.body.edad,
    telefono: req.body.telefono,
    direccion: req.body.direccion
  }
  console.log(usuario);


  axios.post("http://127.0.0.1:8888/nuevo_usuario", usuario)
    .then((response) => {

      let respuesta = response.data;
      console.log(respuesta);
      if(usuario.idUsuario == 0){
        res.render('login');
      }else{
        axios.get("http://127.0.0.1:8888/usuari_por_id/" + usuario.idUsuario)
        .then((response) => {
    
          let usuario = response.data;
          console.log(usuario);
          res.render('miPerfil', {
            usuario: usuario
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



router.post('/miPerfil', function (req, res, next) {

  let IdUsuario = req.body.IdUsuario
  console.log(IdUsuario);

  axios.get("http://127.0.0.1:8888/usuari_por_id/" + IdUsuario)
    .then((response) => {

      let usuario = response.data;
      console.log(usuario);
      res.render('miPerfil', {
        usuario: usuario
      });
    })
    .catch((error) => {
      console.log(error);
      res.render('construccion');
    });


});

/*
router.post('/', function (req, res, next) {


  res.render('');
});

*/