$(function () {
    if (!window.PrimeFaces || !PrimeFaces.widget || !PrimeFaces.widget.SelectCheckboxMenu) {
        return;
    }

    var proto = PrimeFaces.widget.SelectCheckboxMenu.prototype;

    // 1. Sempre abre para baixo — substitui alignPanel para usar collision:"none"
    //    O original usa collision:"flipfit" + transform-origin dinâmico, que causa
    //    a animação para cima mesmo após correção de posição.
    proto.alignPanel = function () {
        var offset = "fixed" === this.panel.css("position")
            ? "-" + $(window).scrollLeft() + " -" + $(window).scrollTop()
            : null;
        var styleAttr = this.panel.attr("style");

        this.panel.css({ left: "", top: "", "z-index": PrimeFaces.nextZindex(), "transform-origin": "center top" });

        if (this.panel.parent().attr("id") === this.id) {
            this.panel.css({ left: "0px", top: this.jq.innerHeight() + "px" });
        } else {
            var posOpts = { my: "left top", at: "left bottom", of: this.jq, collision: "none" };
            if (offset) posOpts.offset = offset;
            this.panel.position(posOpts);
        }

        if (!this.widthAligned && this.panel.width() < this.jq.width()
                && !(styleAttr && styleAttr.toLowerCase().indexOf("width") !== -1)) {
            this.panel.width(this.jq.width());
            this.widthAligned = true;
        }
    };

    // 2. Mantém painel aberto ao rolar — remove o scrollHandler logo após ser registrado
    //    em bindPanelEvents (chamado no onEntered, depois que o painel fica visível).
    var origBindPanelEvents = proto.bindPanelEvents;
    proto.bindPanelEvents = function () {
        origBindPanelEvents.call(this);
        if (this.scrollHandler) {
            this.scrollHandler.unbind();
        }
    };
});
