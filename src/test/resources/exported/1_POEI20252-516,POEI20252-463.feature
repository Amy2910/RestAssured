Feature: Test login

	@POEI20252-516 @AmyOrange
	Scenario: Connexion réussie avec des identifiants invalides
		Given utilisateur est sur la page de connexion de url "https://parabank.parasoft.com"
		When il entre son identifiant "invalide" et son mot de passe "mauvaismdp"
		#Then il voit un message d'erreur
		
	@POEI20252-463 @AmyOrange
	Scenario: Connexion réussie avec des identifiants valides
		Given utilisateur est sur la page de connexion de url "https://parabank.parasoft.com"
		When il entre son identifiant "john" et son mot de passe "demo"
		#Then il est connecté et voit son tableau de bord
		
