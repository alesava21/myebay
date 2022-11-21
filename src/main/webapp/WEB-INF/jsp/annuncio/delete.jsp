<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Elimina annuncio</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sicuro di voler procedere all'eliminazione dell'annuncio?</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo Annuncio:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${delete_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data creazione:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_annuncio_attr.data}" /></dd>
					    	</dl>
					    	
					    </div>
					    
					    <div class='card-footer'>
					    <form action="${pageContext.request.contextPath}/annuncio/executeDelete" method="post">
					    	<input type="hidden" name="idAnnuncio" value="${delete_annuncio_attr.id}">
					    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Conferma</button>
					        	<a href="${pageContext.request.contextPath }/index" class='btn btn-outline-secondary' style='width:80px'>
					        	    <i class='fa fa-chevron-left'></i> Annulla
					       		</a>
					    </form>
					    </div>
					</div>	
			  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>