$(document).ready(){
    function animatethis(targetElement, speed) {
        $(targetElement).animate({ marginLeft: "+=250px"},
        {
            duration: speed,
            complete: function ()
            {
                targetElement.animate({ marginLeft: "-=250px" },
                {
                    duration: speed,
                    complete: function ()
                    {
                        animatethis(targetElement, speed);
                    }
                });
            }
        });
    };

    animatethis($('.swingimage'), 5000);
}
