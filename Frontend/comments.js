document.getElementById("comment-post").addEventListener("click", function (event) {
    event.preventDefault();

    CreateComment();
});

function CreateComment() {
    let content = document.getElementById("comment-content").value;

    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let body = {
        id: Math.floor(Math.random() * (1000 - 1) + 1),
        userId: 1,
        articleId: 1,
        content,
        date: {
            year: 2012,
            month: 4,
            day: 23
        }
    }

    console.log(body);

    let request = new Request("http://localhost:5555/comments", {
        method: "POST",
        headers: headers,
        body: JSON.stringify(body),
    });

    fetch(request).then(function (response) {
        if (response.ok) {
            response.json().then(function (data) {
                console.log(data);
            });
        } else {
            console.log("Network response was not ok.");
        }
    }).catch(function (error) {
        console.log("There has been a problem with your fetch operation: " + error.message);
    });
}
