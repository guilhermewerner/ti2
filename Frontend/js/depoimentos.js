document.getElementById("testimonial-post").addEventListener("click", function (event) {
    event.preventDefault();
    CreateTestimonial();
});

function CreateTestimonial() {
    let name = document.getElementById("testimonial-name").value;
    let location = document.getElementById("testimonial-location").value;
    let description = document.getElementById("testimonial-content").value;
    let userId = window.sessionStorage.getItem('session_id');

    const date = new Date();

    let body = {
        id: Math.floor(Math.random() * (1000 - 1) + 1),
        name,
        description,
        userId,
        type: "None",
        location,
        images: [],
        recommendations: [],
        date: {
            year: date.getFullYear(),
            month: date.getMonth() + 1,
            day: date.getDate()
        }
    }

    axios.post("http://localhost:5555/testimonials", body, { headers: { "Content-Type": "application/json" } })
        .then(function (response) {
            axios.get(`http://localhost:5555/testimonials/${body.id}`)
                .then(function (response) {
                    let data = response.data;
                    console.log(data);

                    let recommendations = "";
                    data.recommendations.forEach(element => {
                        recommendations += `
                            <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
                                <p class="mb-1">
                                    ${element}
                                </p>
                            </a>
                        `;
                    });

                    let content = "";
                    content += `
                        <div class="mb-5 pb-2">
                            <h2>${body.name} #${body.id}</h2>
                            <p>
                                ${body.description}
                            </p>
                        </div>
                        <div class="list-group">
                            ${recommendations}
                        </div>
                    `;

                    let wrapper = document.getElementById("depoiment-wrapper");
                    wrapper.innerHTML = content;
                })
                .catch(function (error) {
                    console.log(error.statusText);
                });
        })
        .catch(function (error) {
            console.log(error.statusText);
        });
}
