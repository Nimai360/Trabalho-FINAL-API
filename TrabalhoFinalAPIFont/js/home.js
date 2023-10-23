if (localStorage.getItem("OAuthToken") == "") {
  window.location.href = "login.html";
}
const base_url = "http://localhost:8080";

async function getUsuario(token) {
  const urlApi = `${base_url}/usuarios/perfil/`;

  try {
    const response = await fetch(urlApi, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`, // Passa o token no cabeçalho "Authorization"
      },
    });

    if (!response.ok) {
      throw new Error(`A solicitação GET falhou com status ${response.status}`);
    }

    const data = await response.json(); // Obtém os dados da resposta

    setUserLogadoData(data);
    return data;
  } catch (error) {
    console.error("Erro na solicitação GET:", error);
  }
}

// Agora você pode chamar a função getUsuario passando o token retornado anteriormente
// Certifique-se de que o token esteja disponível no escopo onde você chama essa função

getUsuario(localStorage.getItem("OAuthToken"));

function setUserLogadoData(data) {
  document.getElementById("email").innerHTML = data.email;
  document.getElementById("name").innerHTML = data.nome + " " + data.sobrenome;
  document.getElementById("descricao").innerHTML =
    "Data de Nascimento: " + converterData(data.dataNascimento);
  if (data.url != null) {
    document.getElementById("fotoPerfil").setAttribute("src", data.url);
  }

  console.log(data.url);

  if (data.postagens.length > 0) {
    data.postagens.forEach((element) => {
      getPostagens(element.id, data.url);
    });
  }
}

function converterData(data) {
  const dataISO = new Date(data);
  const dia = dataISO.getDate().toString().padStart(2, "0");
  const mes = (dataISO.getMonth() + 1).toString().padStart(2, "0");
  const ano = dataISO.getFullYear();
  return `${dia}/${mes}/${ano}`;
}

function deslogar() {
  event.preventDefault();
  localStorage.setItem("OAuthToken", "");
  location.replace("login.html");
}

async function getPostagens(id_post, foto_perfil) {
  const urlApi = `${base_url}/posts/` + id_post;
  let token = localStorage.getItem("OAuthToken");

  try {
    const response = await fetch(urlApi, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`, // Passa o token no cabeçalho "Authorization"
      },
    });

    if (!response.ok) {
      throw new Error(`A solicitação GET falhou com status ${response.status}`);
    }

    const data = await response.json(); // Obtém os dados da resposta
    await adicionarPost(data, foto_perfil);
    return data;
  } catch (error) {
    console.error("Erro na solicitação GET:", error);
  }
}
function adicionarPost(data, foto_perfil) {
  // Crie um novo elemento li
  const novoLi = document.createElement("li");
  novoLi.className = "post-timeline";
  novoLi.id = `post${data.id}`;

  // Crie a estrutura interna do novo li
  novoLi.innerHTML = `
    <div class="perfil-post">
      <img src="${foto_perfil}" alt="" class="foto-post">
      <div class="texto-post">
        <div class="texto-post1">
          <h2 class="nome-post">${data.usuario.nome} ${data.usuario.sobrenome}</h2>
        </div>
        <p class="conteudo-post">${data.conteudo}</p>
      </div>
    </div>
    <div class="botoes-postagem">
      <div class="comentarios">
        <input type="checkbox" id="menu" class="container__botao">
        <label for="menu" class="container__rotulo">
          <span class="cabecalho__menu-hamburguer container__imagem">
            <img src="assets/src/icons (1).png" alt="" class="seta">Comentarios
          </span>
        </label>
        <ul class="lista-menu">
          <li class="lista-menu__titulo"></li>
          <li class="lista-menu__item">
            <a href="#" class="lista-menu__link"></a>
          </li>
        </ul>
      </div>
    </div>
    <img src="assets/src/div (1).png" alt="">
    <ul id="comentarios">        
    </ul>
    <div class="comentar">
      <textarea placeholder="Comentar" cols="70" rows="10" class="input-comentar"></textarea>
      <button type="submit" class="enviar">
        <img src="certo" alt="">
        <img src="assets/src/certo.png" alt="">
      </button>
    </div>
  `;

  // Selecione o elemento ul com a classe "listas-postagens"
  const ulListasPostagens = document.querySelector(".listas-postagens");
  // Adicione o novo li ao ul
  ulListasPostagens.appendChild(novoLi);

  data.comentarios.forEach((element) => {
    adicionarComentarios(element, data.id);
  });
}

async function adicionarComentarios(data, id_post) {
  // Crie um novo elemento li
  const novoLi = document.createElement("li");
  novoLi.className = "comment-timeline";
  novoLi.id = `comment${data.id}`;

  // Crie a estrutura interna do novo li
  novoLi.innerHTML = `
    <li class="post-comments">
        <p>${data.texto}</p>
    </li>
    `;

  // Suponha que você tenha o ID da postagem à qual este comentário pertence
  const postId = id_post;

  // Selecione o elemento ul com a classe "comentarios" dentro da postagem correta
  const ulListasPostagens = document.querySelector(
    `#post${postId} .comentarios`
  );

  // Adicione o novo li ao ul dentro da postagem correta
  ulListasPostagens.appendChild(novoLi);
}

function addpostagem() {
  let post = document.getElementById("text_post").value;

  const token = localStorage.getItem("OAuthToken"); // Substitua pelo seu token de autorização
  const url = "http://localhost:8080/posts"; // Substitua pela URL da sua API

  // Dados a serem enviados no corpo da requisição
  const data = {
    conteudo: post,
  };

  fetch(url, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`, // Adicione o token de autorização no cabeçalho
      "Content-Type": "application/json", // Indique que o corpo da requisição é JSON
    },
    body: JSON.stringify(data), // Converte o objeto de dados em uma string JSON
  })
    .then((response) => {
      if (response.ok) {
        return response.json(); // Se a resposta for bem-sucedida, você pode lidar com os dados aqui
      } else {
        throw new Error("Falha na requisição");
      }
    })
    .then((data) => {
      // Faça algo com os dados da resposta, se necessário
      console.log(data);
    })
    .catch((error) => {
      console.error("Erro na requisição:", error);
    });
}
