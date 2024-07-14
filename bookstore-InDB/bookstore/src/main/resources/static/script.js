document.addEventListener('DOMContentLoaded', function () {
    const bookForm = document.getElementById('bookForm');
    const booksTable = document.getElementById('booksTable').getElementsByTagName('tbody')[0];

    const apiUrl = '/api/books';

    bookForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const bookId = document.getElementById('bookId').value;
        const title = document.getElementById('title').value;
        const author = document.getElementById('author').value;
        const isbn = document.getElementById('isbn').value;
        const price = document.getElementById('price').value;

        const book = { title, author, isbn, price };

        if (bookId) {
            updateBook(bookId, book);
        } else {
            createBook(book);
        }
    });

    function createBook(book) {
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
        .then(response => response.json())
        .then(data => {
            clearForm();
            fetchBooks();
        });
    }

    function updateBook(id, book) {
        fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
        .then(response => response.json())
        .then(data => {
            clearForm();
            fetchBooks();
        });
    }

    function deleteBook(id) {
        fetch(`${apiUrl}/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            fetchBooks();
        });
    }

    function fetchBooks() {
        fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            booksTable.innerHTML = '';
            data.forEach(book => {
                const row = booksTable.insertRow();
                row.insertCell(0).textContent = book.id;
                row.insertCell(1).textContent = book.title;
                row.insertCell(2).textContent = book.author;
                row.insertCell(3).textContent = book.isbn;
                row.insertCell(4).textContent = book.price;

                const actionsCell = row.insertCell(5);
                actionsCell.classList.add('actions');
                const editButton = document.createElement('button');
                editButton.textContent = 'Edit';
                editButton.onclick = () => {
                    document.getElementById('bookId').value = book.id;
                    document.getElementById('title').value = book.title;
                    document.getElementById('author').value = book.author;
                    document.getElementById('isbn').value = book.isbn;
                    document.getElementById('price').value = book.price;
                };
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.classList.add('delete');
                deleteButton.onclick = () => {
                    deleteBook(book.id);
                };
                actionsCell.appendChild(editButton);
                actionsCell.appendChild(deleteButton);
            });
        });
    }

    function clearForm() {
        document.getElementById('bookId').value = '';
        bookForm.reset();
    }

    fetchBooks();
});
