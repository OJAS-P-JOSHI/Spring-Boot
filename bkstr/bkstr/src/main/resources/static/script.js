document.addEventListener("DOMContentLoaded", () => {
    const bookContainer = document.getElementById("books");

    fetch("/books")
        .then(response => response.json())
        .then(books => {
            books.forEach(book => {
                const bookDiv = document.createElement("div");
                bookDiv.className = "book";
                bookDiv.innerHTML = `
                    <h2>${book.title}</h2>
                    <p><strong>Author:</strong> ${book.author}</p>
                    <p><strong>Price:</strong> $${book.price.toFixed(2)}</p>
                `;
                bookContainer.appendChild(bookDiv);
            });
        })
        .catch(error => {
            const errorMessage = document.createElement("p");
            errorMessage.textContent = "Error fetching books.";
            errorMessage.style.color = "red";
            bookContainer.appendChild(errorMessage);
            console.error("Error fetching books:", error);
        });
});
