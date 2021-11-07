const noticias = [
    {
        id: 0,
        titulo: "Cães resgatados de maus-tratos estão disponíveis para adoção em Nova Petrópolis",
        conteudo:
            "Eles foram apreendidos em uma operação em outubro de 2020. Todos estão sadios e castrados",
        url: "artigos3.html",
    },
    {
        id: 1,
        titulo: "Multa por maus-tratos a animais vai aumentar em SC",
        conteudo:
            "O governador Carlos Moisés vai sancionar nesta segunda-feira (17) uma nova lei estadual que aumenta a multa...",
        url: "artigos2.html",
    },
    {
        id: 2,
        titulo: "Operação Pará Pet fiscaliza casos de maus-tratos contra animais na região metropolitana de Belém",
        conteudo:
            "Operação foi deflagrada nesta sexta-feira, 30 pela Polícia Civil.",
        url: "artigos4.html",
    },
];

var db_favoritados = { noticiasFavoritadas: [] };

function Favoritar(id) {
    let starCompleta = document.getElementById(`favoritado${id}`);
    let starVazia = document.getElementById(`${id}`);

    starCompleta.classList.add("aparecer");
    starCompleta.classList.remove("desaparecer");
    starVazia.classList.add("desaparecer");
    starVazia.classList.remove("aparecer");

    let obj = localStorage.getItem("db_favoritados");
    //let noticiaAtual = noticias[id]
    console.log(obj);

    if (obj == null || (obj = "undefined"))
        db_favoritados.noticiasFavoritadas.push(id);
    else {
        db_favoritados = JSON.parse(obj);
        if (db_favoritados.noticiasFavoritadas.includes(id) == false) {
            db_favoritados.noticiasFavoritadas.push(id);
        }
    }

    localStorage.setItem("db_favoritados", JSON.stringify(db_favoritados));
}

function Desfavoritar(id) {
    //console.log("ola entrei");
    id = id.substring(10);
    console.log(id);
    let starCompleta = document.getElementById(`favoritado${id}`);
    let starVazia = document.getElementById(`${id}`);

    starCompleta.classList.remove("aparecer");
    starCompleta.classList.add("desaparecer");
    starVazia.classList.remove("desaparecer");
    starVazia.classList.add("aparecer");

    let obj = JSON.parse(localStorage.getItem("db_favoritados"));
    delete obj.db_favoritados[id];
    localStorage.setItem("db_favoritados", JSON.stringify(db_favoritados));
}

function exibirFavoritados() {
    let obj = JSON.parse(localStorage.getItem("db_favoritados")).noticiasFavoritadas;

    for (let i = 0; i < obj.length; i++) {
        let starCompleta = document.getElementById(`favoritado${i}`);
        let starVazia = document.getElementById(`${i}`);

        starCompleta.classList.add("aparecer");
        starCompleta.classList.remove("desaparecer");
        starVazia.classList.add("desaparecer");
        starVazia.classList.remove("aparecer");
    }
}

onload = () => {
    exibirFavoritados();
};

// Desativado