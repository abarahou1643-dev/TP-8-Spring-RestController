// Script JavaScript pour la page d'accueil
document.addEventListener('DOMContentLoaded', function() {
    console.log('🏦 Microservice Banque - Page chargée');

    // Animation des cartes au chargement
    const cards = document.querySelectorAll('.card');
    cards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';

        setTimeout(() => {
            card.style.transition = 'all 0.6s ease';
            card.style.opacity = '1';
            card.style.transform = 'translateY(0)';
        }, index * 200);
    });

    // Effet de survol amélioré pour les boutons
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(button => {
        button.addEventListener('mouseenter', function() {
            this.style.transform = 'scale(1.05)';
        });

        button.addEventListener('mouseleave', function() {
            this.style.transform = 'scale(1)';
        });
    });

    // Affichage de l'heure actuelle
    function updateTime() {
        const now = new Date();
        const timeString = now.toLocaleTimeString('fr-FR');
        const dateString = now.toLocaleDateString('fr-FR', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });

        console.log(`🕒 ${dateString} - ${timeString}`);
    }

    updateTime();
    setInterval(updateTime, 60000); // Mise à jour chaque minute
});