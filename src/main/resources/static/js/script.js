// Sélectionner l'élément input
const imageInput = document.getElementById('imageInput');

// Sélectionner le conteneur d'image
const imageContainer = document.getElementById('imageContainer');

// Écouter l'événement de modification du champ d'entrée
imageInput.addEventListener('change', function() {
    // Vérifier si un fichier est sélectionné
    if (imageInput.files && imageInput.files[0]) {
        // Créer un objet FileReader
        const reader = new FileReader();

        // Lorsque la lecture du fichier est terminée
        reader.onload = function(e) {
            // Créer la balise <img>
            const img = document.createElement('img');
            img.src = e.target.result;
            img.alt = 'Image Preview';

            // Ajouter la balise <img> au conteneur d'image
            imageContainer.innerHTML = '';
            imageContainer.appendChild(img);
            img.style.width = '150px';
            img.style.height = 'auto';
        }

        // Lire le fichier en tant que URL de données (data URL)
        reader.readAsDataURL(imageInput.files[0]);
    } else {
        // Si aucun fichier n'est sélectionné, vider le conteneur d'image
        imageContainer.innerHTML = '';
    }
});
