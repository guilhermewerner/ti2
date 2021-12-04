function PopulateArticles() {
    let wrapper = document.getElementById("semelhants");
    let response = JSON.parse(this.responseText);

    console.log(response.articles);

    let content = "";

    response.articles.forEach(element => {
        content += `
        <div class="card my-4">
            <div class="card-body p-3">
                <div class="d-flex align-items-center">
                    <div class="flex-grow-1 ms-3">
                        <b>
                            <a class="nav-link" href="${element.url}" target="_blank">${element.title}</a>
                        </b>
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
    xhr.open("GET", "https://newsapi.org/v2/everything?q=animais+animal&sortBy=relevancy&pageSize=3&apiKey=8d780d9e683a4182973a1c2823134384");
    xhr.send();
})
