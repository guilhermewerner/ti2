function exibeNoticias() {
    let divTop = document.getElementById("row-blog");
    let texto = "";

    let dados = JSON.parse(this.responseText);
    console.log(dados);
    for (i = 0; dados.totalResults != 0 && i < 3; i++) {
        let noticia = dados.articles[i];
        texto = texto + `
        <div class="col-lg col-sm mb-3">
            <div class="card card-blog shadow-sm">
                <img width="300" height="300"
                    src="${noticia.urlToImage}"
                    class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${noticia.title}</h5>
                    <p class="card-text article-text">
                        ${noticia.description}
                    </p>
                    <div class="d-flex justify-content-end">
                        <a class="card-link text-muted" href="${noticia.url}" target="_blank">Leia Mais</a>
                    </div>
                    <div class="d-flex justify-content-space-between" >
                        <div class="facebook-button me-2">
                            <a
                                href="https://www.facebook.com/sharer/sharer.php?u=${noticia.url}" target="_blank">
                                <img width="25" height="25"
                                    src="https://ayltoninacio.com.br/img/s/18w50.jpg" alt="">
                            </a>
                        </div>
                        <div class="linkedin-button me-2">
                            <a
                                href="https://www.linkedin.com/shareArticle?mini=true&url=${noticia.url}" target="_blank">
                                <img width="25" height="25"
                                    src="https://image.flaticon.com/icons/png/512/174/174857.png" alt="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        `;
    }
    if (dados.totalResults > 0)
        divTop.innerHTML = texto;
}

$(window).on('load', function () {
    let xhr = new XMLHttpRequest();
    xhr.onload = exibeNoticias;
    let resultsNumber = 3;
    let url = "https://trabalho-ti-news.herokuapp.com/featured";
    xhr.open(
        "GET",
        url
    );
    xhr.send();
})
