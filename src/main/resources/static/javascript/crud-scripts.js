var array = [];
var headers = [];

//código desenvolvido a partir de https://getbootstrap.com/docs/4.0/components/modal/#varying-modal-content
$('#nova-disciplina-modal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id') // Extract info from data-* attributes
	if (id != undefined) {
		var modal = $(this)
		modal.find('.modal-content #id').val(array[id].ID)
		modal.find('.modal-content #nome').val(array[id].Nome)
		modal.find('.modal-content #professor').val(array[id].Professor)
		modal.find('.modal-content #codigo').val(array[id].Classroom)
		modal.find('.modal-content ' + '#periodo' + array[id].Período).attr("checked", true)
	}
})


$('#excluir-disciplina-modal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id') // Extract info from data-* attributes
	var modal = $(this)
	modal.find('.modal-content #codigo').val(id)
	modal.find('.modal-content #codigo').text(id)
})

$('#tabela-dados .campos').each(function(index, item) {
	headers[index] = $(item).html();
});

$('#tabela-dados tr').has('td').each(function() {
	var arrayItem = {};
	$('.dados', $(this)).each(function(index, item) {
		arrayItem[headers[index]] = $(item).html();
	});
	array.push(arrayItem);
});

