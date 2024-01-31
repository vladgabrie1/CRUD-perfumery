document.addEventListener("DOMContentLoaded", function () {
    const navbar = document.getElementById("headerNav");

    window.addEventListener("scroll", function () {
        const shouldChangeStyle = window.scrollY > 50;

        navbar.style.background = shouldChangeStyle ? "rgba(247,239,229,0.72)" : "rgba(0,0,0,0)";
        navbar.style.boxShadow = shouldChangeStyle ? "0px 4px 8px rgba(0, 0, 0, 0.1)" : null;
    });

    const detailsButtons = document.querySelectorAll('.details-btn-js');
    const deleteButtons = document.querySelectorAll('.delete-button');

    detailsButtons.forEach(button => {
        button.addEventListener('click', function () {
            showDetails(this);
        });
    });
});

function showDetails(row) {
    const perfumeId = row.getAttribute("perfume-id");
    const noteId = row.getAttribute("note-id");

    if (perfumeId) {
        window.location.href = `/perfume/details?perfumeId=${perfumeId}`;
    }

    if (noteId) {
        window.location.href = `/note/details?noteId=${noteId}`;
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const deleteButtons = document.querySelectorAll('.delete-button');

    deleteButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.stopPropagation();
            const entityId = button.dataset.entityId;
            confirmAndDelete(entityId);
        });
    });

    function confirmAndDelete(id) {
        const isConfirmed = confirm("Are you sure you want to delete this entity?");
        if (isConfirmed) {
            const deleteForm = document.getElementById(`deleteForm-${id}`);
            if (deleteForm) {
                deleteForm.submit();
            }
        }
    }
});


