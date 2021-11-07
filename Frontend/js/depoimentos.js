document.getElementById("testimonial-post").addEventListener("click", function (event) {
    event.preventDefault();

    CreateTestimonial();
});

function CreateTestimonial() {
    let name = document.getElementById("testimonial-name").value;
    let description = document.getElementById("testimonial-content").value;

    let headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    let body = {
        id: Math.floor(Math.random() * (1000 - 1) + 1),
        name,
        description,
        userId: 1,
        type: "None",
        location: "Brasil",
        images: [
            "linda.png"
        ],
        date: {
            year: 2012,
            month: 4,
            day: 23
        }
    }

    console.log(body);

    let request = new Request("http://localhost:5555/testimonials", {
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
