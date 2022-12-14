<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
            
          </li>
          <li class="nav-item">
	          </li>
          <li class="nav-item">
          </li>
          <sec:authorize access="isAuthenticated()">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/annuncio/search">Gestione annunci</a></li>
              	<li><a class="dropdown-item" href="${pageContext.request.contextPath}/annuncio/insert">Inserisci annunci</a></li>
				<li><a class="dropdown-item" href="${pageContext.request.contextPath}/acquisto/search">Acquisti effettuati</a></li>            		
            </ul> 
          </li>
          
          </sec:authorize>
           <sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Ricerca Utenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/insert">Inserisci Utente</a>
		        </div>
		      </li>
		   </sec:authorize>
        </ul>
      </div>
      
      <sec:authorize access="isAuthenticated()">
	      <div class="col-md-3 text-end">
	       <li class="nav-item dropdown text-primary">
            <a style="margin-top: -25px" class="nav-link dropdown-toggle text-light" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
             <li> <p class="navbar-text text-center" style = "color:black">Utente: <sec:authentication property="name"/> (Credito: ${userInfo.creditoResiduo}$ ${userInfo.nome } ${userInfo.cognome })</p>
              <li> <div class="dropdown-divider" ></div><li><a class="dropdown-item" href="${pageContext.request.contextPath}/account/cambiapassword">Cambia Password</a>
              </li>
				<li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>            		
            </ul> 
          </li>
	      </div>
      </sec:authorize>
      
      
      
      <sec:authorize access="!isAuthenticated()">
	      <div class="col-sm-1 text-end">
	        <p class="navbar-text"><a href="${pageContext.request.contextPath}/login">Login</a><br><a href="${pageContext.request.contextPath}/utente/registrazione">Registrazione</a></p>	       
	      </div>
      </sec:authorize>
      
    </div>
  </nav>
  
  
</header>