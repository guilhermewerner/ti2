function PopulateArticles() {
    let wrapper = document.getElementById("articles");
    let response = JSON.parse(this.responseText);

    console.log(response.articles);

    let content = "";

    response.articles.forEach(element => {
        content += `
        <div class="col-lg-4 col-md-6 mb-5">
            <div class="card">
                <img src="${element.urlToImage}" class="card-img-top article-image" alt="${element.title}"/>
                <div class="card-body">
                    <h5 class="card-title">${element.title}</h5>
                    <p class="card-text article-text">${element.description}</p>
                    <div class="d-flex justify-content-end">
                        <a class="card-link text-muted" href="${element.url}" target="_blank">Leia Mais</a>
                    </div>
                </div>
            </div>
        </div>
        `;
    });

    wrapper.innerHTML = content;
}

$(window).on('load', function () {
    let xhr = new XMLHttpRequest();
    xhr.onload = PopulateArticles;
    xhr.open("GET", "https://newsapi.org/v2/everything?q=animais+animal&sortBy=relevancy&pageSize=15&apiKey=8d780d9e683a4182973a1c2823134384");
    xhr.send();
})
