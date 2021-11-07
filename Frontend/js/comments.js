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

function GetComments() {
    let content = document.getElementById("comment-content").value;

    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let request = new Request("http://localhost:5555/comments", {
        method: "GET",
        headers: headers,
    });

    fetch(request).then(function (response) {
        if (response.ok) {
            response.json().then(function (data) {
                let content = "";
                let wrapper = document.getElementById("comments-list");

                console.log(data);

                for (let i = 0; i < data.length; i++) {
                    content = content + `
                        <div class="card mb-3">
                            <div class="card-body p-4">
                                <div class="d-flex align-items-center mb-4">
                                    <div class="flex-shrink-0">
                                        <img src="./img/user.png" alt="...">
                                    </div>
                                    <div class="flex-grow-1 ms-3">
                                        ${data[i].userId}
                                    </div>
                                </div>
                                <p class="card-text">
                                    ${data[i].content}
                                </p>
                            </div>
                        </div>
                    `;
                }

                wrapper.innerHTML = content;
            });
        } else {
            console.log("Network response was not ok.");
        }
    }).catch(function (error) {
        console.log("There has been a problem with your fetch operation: " + error.message);
    });
}

GetComments();
