$(function () {
  const API = '/api/quartos';
  const API_HOTEIS = '/api/hoteis';
  const moeda = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' });

  function esc(t) {
    return $('<div>').text(t == null ? '' : t).html();
  }

  function mensagem(texto, tipo) {
    $('#mensagem').attr('class', tipo).text(texto).show();
    setTimeout(function () { $('#mensagem').hide(); }, 5000);
  }

  function erroAjax(xhr) {
    const msg = (xhr.responseJSON && xhr.responseJSON.erro) || 'Falha na comunicação com o servidor';
    mensagem(msg, 'erro');
  }

  function limparForm() {
    $('#formQuarto')[0].reset();
    $('#id').val('');
    $('#tituloForm').text('Novo quarto');
    const filtro = $('#filtroHotel').val();
    if (filtro) $('#hotel').val(filtro);
  }

  // Preenche os <select> de hotel (formulário e filtro)
  function carregarHoteis() {
    const params = new URLSearchParams(window.location.search);
    const hotelIdUrl = params.get('hotelId') || '';

    return $.getJSON(API_HOTEIS).done(function (hoteis) {
      const $sel = $('#hotel').empty();
      const $fil = $('#filtroHotel').empty().append('<option value="">Todos os hotéis</option>');
      if (!hoteis.length) {
        $sel.append('<option value="">Cadastre um hotel primeiro</option>');
      }
      hoteis.forEach(function (h) {
        $sel.append('<option value="' + h.id + '">' + esc(h.nome) + '</option>');
        $fil.append('<option value="' + h.id + '">' + esc(h.nome) + '</option>');
      });
      if (hotelIdUrl) {
        $fil.val(hotelIdUrl);
        $sel.val(hotelIdUrl);
      }
    }).fail(erroAjax);
  }

  function carregarQuartos() {
    const hotelId = $('#filtroHotel').val();
    $.getJSON(API, hotelId ? { hotelId: hotelId } : {}).done(function (quartos) {
      const $tb = $('#tabela').empty();
      if (!quartos.length) {
        $tb.append('<tr><td colspan="6" class="vazio">Nenhum quarto cadastrado.</td></tr>');
        return;
      }
      quartos.forEach(function (q) {
        $tb.append(
          '<tr>' +
          '<td>' + q.id + '</td>' +
          '<td>' + esc(q.hotel.nome) + '</td>' +
          '<td>' + esc(q.numero) + '</td>' +
          '<td>' + esc(q.tipo) + '</td>' +
          '<td class="num">' + moeda.format(q.diaria) + '</td>' +
          '<td class="botoes">' +
            '<button class="pequeno btn-editar" data-id="' + q.id + '">Editar</button> ' +
            '<button class="pequeno perigo btn-excluir" data-id="' + q.id + '">Excluir</button>' +
          '</td></tr>'
        );
      });
    }).fail(erroAjax);
  }

  $('#formQuarto').on('submit', function (e) {
    e.preventDefault();
    const id = $('#id').val();
    const hotelId = $('#hotel').val();
    if (!hotelId) {
      mensagem('Selecione um hotel.', 'erro');
      return;
    }
    const dados = {
      hotel: { id: parseInt(hotelId, 10) },
      numero: $('#numero').val().trim(),
      tipo: $('#tipo').val().trim(),
      diaria: parseFloat($('#diaria').val())
    };
    $.ajax({
      url: id ? API + '/' + id : API,
      method: id ? 'PUT' : 'POST',
      contentType: 'application/json',
      data: JSON.stringify(dados)
    }).done(function () {
      mensagem(id ? 'Quarto atualizado com sucesso.' : 'Quarto cadastrado com sucesso.', 'ok');
      limparForm();
      carregarQuartos();
    }).fail(erroAjax);
  });

  $('#tabela').on('click', '.btn-editar', function () {
    $.getJSON(API + '/' + $(this).data('id')).done(function (q) {
      $('#id').val(q.id);
      $('#hotel').val(q.hotel.id);
      $('#numero').val(q.numero);
      $('#tipo').val(q.tipo);
      $('#diaria').val(q.diaria);
      $('#tituloForm').text('Editar quarto #' + q.id);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }).fail(erroAjax);
  });

  $('#tabela').on('click', '.btn-excluir', function () {
    const id = $(this).data('id');
    if (!confirm('Excluir o quarto #' + id + '?')) return;
    $.ajax({ url: API + '/' + id, method: 'DELETE' }).done(function () {
      mensagem('Quarto excluído.', 'ok');
      carregarQuartos();
    }).fail(erroAjax);
  });

  $('#filtroHotel').on('change', function () {
    if (!$('#id').val() && $(this).val()) $('#hotel').val($(this).val());
    carregarQuartos();
  });

  $('#btnCancelar').on('click', limparForm);

  carregarHoteis().always(carregarQuartos);
});
