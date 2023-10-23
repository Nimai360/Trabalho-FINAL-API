if(localStorage.getItem("OAuthToken") != ""){
  window.location.href = "index.html";
}
async function logar() {
    event.preventDefault();
    let email = document.getElementById("email").value;
    let password = document.getElementById("senha").value;
  
    const base_url = "http://localhost:8080";
    const urlApi = `${base_url}/login`;
    try {
      let token = await verifyUser(urlApi, email, password);
      localStorage.setItem("OAuthToken", token);
      location.replace("index.html");
      // Agora você pode prosseguir com o uso do token para fazer o login no sistema.
    } catch (error) {
      console.error("Erro na solicitação:", error);
    }
  }
  
  async function verifyUser(urlApi, username, password) {
    try {
      const response = await fetch(urlApi, {
        method: "POST",
        body: JSON.stringify({
          username: username,
          password: password,
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8",
        },
      });
  
      if (!response.ok) {
        throw new Error(`A solicitação falhou com status ${response.status}`);
      }
  
      const token = response.headers.get("Authorization"); // Obter o token do cabeçalho
  
      if (token) {
        return token.replace("Bearer ", "");
      } else {
        return null; // Ou qualquer valor apropriado para indicar que o token não foi encontrado
      }
    } catch (error) {
      console.error("Erro na solicitação:", error);
      throw error;
    }
  }
  