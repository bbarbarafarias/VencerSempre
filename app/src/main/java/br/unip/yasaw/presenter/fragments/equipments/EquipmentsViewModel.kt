package br.unip.yasaw.presenter.fragments.equipments

import br.unip.yasaw.R
import br.unip.yasaw.data.models.EquipmentModel
import br.unip.yasaw.presenter.base.BaseViewModel

class EquipmentsViewModel : BaseViewModel() {

    fun getEquipments(): MutableList<EquipmentModel> {
        return mutableListOf(
            EquipmentModel(
                0,
                "Caixa Acústica",
                "O alto-falante, também chamado de caixa acústica, é um componente essencial em sistemas de áudio, projetado para reproduzir com fidelidade e clareza o som proveniente de fontes eletrônicas. Composto por elementos como falantes, tweeters e woofers, sua construção busca a reprodução precisa das frequências sonoras, proporcionando uma experiência auditiva de alta qualidade. Amplamente utilizado em ambientes residenciais, profissionais e de entretenimento, o alto-falante desempenha um papel crucial na transmissão de música, filmes, palestras e outros conteúdos sonoros, elevando a experiência do usuário a um nível superior.",
                R.drawable.ic_product_0
            ),
            EquipmentModel(
                1,
                "Caixa Amplificada",
                "A caixa de som amplificada é um equipamento de áudio que combina alto-falantes e amplificadores em um único dispositivo, projetado para reproduzir e amplificar o som de fontes eletrônicas. Com sua construção compacta e integrada, a caixa de som amplificada oferece uma solução conveniente e eficiente para a reprodução de música, palestras, apresentações e outros conteúdos sonoros em ambientes diversos. Equipada com recursos como entradas de áudio, controles de volume e equalização, a caixa de som amplificada proporciona uma experiência de áudio de alta qualidade, tornando-a uma escolha popular para eventos, festas, reuniões e apresentações profissionais.",
                R.drawable.ic_product_6
            ),
            EquipmentModel(
                2,
                "CRT TV",
                "A televisão de tubo de raios catódicos, conhecida como TV CRT, é um aparelho eletrônico que utiliza a tecnologia do tubo de imagem para mostrar conteúdo visual. Composta por um tubo de vidro a vácuo e um feixe de elétrons controlado magneticamente, a TV CRT exibe imagens claras e vívidas em sua tela de vidro. Amplamente utilizada em décadas passadas, a TV CRT foi pioneira na transmissão de programas televisivos para milhões de lares ao redor do mundo. Embora tenha sido em grande parte substituída por tecnologias mais modernas, como LCD e LED, a TV CRT é lembrada por sua qualidade visual e teve um papel importante no avanço da indústria do entretenimento doméstico.",
                R.drawable.ic_product_1
            ),
            EquipmentModel(
                3,
                "TV VCR",
                "A TV VCR é um aparelho eletrônico que combina uma televisão com um videocassete, permitindo aos usuários assistir a programas de TV e gravar conteúdo em fitas magnéticas. Com sua construção integrada e recursos de gravação e reprodução de vídeo, a TV VCR oferece uma solução conveniente para entretenimento doméstico, permitindo aos usuários assistir a filmes, programas de TV e vídeos caseiros em um único dispositivo. Embora tenha sido amplamente substituída por tecnologias mais modernas, como DVD e Blu-ray, a TV VCR é lembrada por sua praticidade e versatilidade, que a tornaram uma escolha popular para consumidores que valorizam a conveniência e a simplicidade.",
                R.drawable.ic_product_8
            ),
            EquipmentModel(
                4,
                "DVD Player",
                "Um DVD player é um dispositivo eletrônico projetado para reproduzir discos digitais de vídeo (DVDs). Composto por componentes como leitores de disco a laser, circuitos de decodificação de vídeo e saídas de áudio e vídeo, o DVD player oferece uma experiência de entretenimento doméstico de alta qualidade. Capaz de reproduzir uma variedade de formatos de vídeo e áudio, como DVD-Video, DVD-Audio e CDs de áudio, o DVD player proporciona aos usuários acesso a uma ampla gama de conteúdos multimídia. Com sua interface intuitiva e recursos avançados, como reprodução de alta definição e capacidade de conexão a outros dispositivos de áudio e vídeo, o DVD player continua a ser uma escolha popular para consumidores que valorizam a conveniência e a qualidade de reprodução.",
                R.drawable.ic_product_2
            ),
            EquipmentModel(
                5,
                "Notebook",
                "Um notebook, também conhecido como laptop, é um dispositivo portátil de computação pessoal que oferece uma ampla gama de recursos de computação em um formato compacto e conveniente. Equipado com um teclado, tela, trackpad e todos os componentes essenciais de um computador, um notebook permite aos usuários realizar uma variedade de tarefas, como navegar na internet, enviar e-mails, criar documentos, assistir a vídeos, jogar jogos e muito mais, em qualquer lugar e a qualquer momento. Com avanços contínuos em tecnologia de hardware e software, os notebooks modernos oferecem desempenho excepcional, duração prolongada da bateria e portabilidade incomparável, tornando-os uma escolha popular para profissionais, estudantes e entusiastas de tecnologia que buscam produtividade e entretenimento em movimento.",
                R.drawable.ic_product_3
            ),
            EquipmentModel(
                6,
                "Projetor de Slides",
                "O projetor de slides é um dispositivo óptico que projeta imagens fotográficas em uma tela ou superfície plana para visualização. Composto por um mecanismo de transporte de slides, uma fonte de luz e um sistema de lentes, o projetor de slides permite aos usuários exibir fotografias, gráficos e outros conteúdos visuais em apresentações, palestras e eventos diversos. Amplamente utilizado em décadas passadas, o projetor de slides foi uma ferramenta essencial para profissionais, educadores e entusiastas de fotografia que desejavam compartilhar imagens de forma eficaz e envolvente. Embora tenha sido em grande parte substituído por tecnologias digitais, como projetores de vídeo e telas de projeção, o projetor de slides é lembrado por sua simplicidade e eficácia na exibição de imagens em grande escala.",
                R.drawable.ic_product_7
            ),
            EquipmentModel(
                7,
                "Datashow",
                "O retroprojetor, também conhecido como datashow, é um dispositivo de projeção de imagem que utiliza transparências para exibir conteúdo visual em uma tela ou superfície plana. Composto por um mecanismo de projeção de luz, uma fonte de luz e uma lente de projeção, o retroprojetor permite aos usuários exibir gráficos, textos e outros conteúdos visuais em apresentações, palestras e eventos diversos. Amplamente utilizado em ambientes educacionais e corporativos, o retroprojetor foi uma ferramenta essencial para profissionais e educadores que desejavam compartilhar informações de forma clara e eficaz. Embora tenha sido em grande parte substituído por tecnologias digitais, como projetores de vídeo e telas de projeção, o retroprojetor é lembrado por sua simplicidade e eficácia na exibição de conteúdo visual em grande escala.",
                R.drawable.ic_product_9
            ),
            EquipmentModel(
                8,
                "Multimídia",
                "O sistema de som multimídia é um conjunto de equipamentos de áudio e vídeo projetados para proporcionar uma experiência de entretenimento imersiva e envolvente. Composto por componentes como alto-falantes, amplificadores, telas de vídeo e reprodutores de mídia, o sistema de som multimídia oferece uma ampla gama de recursos e funcionalidades para usuários que desejam desfrutar de música, filmes, jogos e outros conteúdos multimídia em alta qualidade. Com sua capacidade de reproduzir uma variedade de formatos de áudio e vídeo, o sistema de som multimídia é uma escolha popular para consumidores que valorizam a experiência de entretenimento em casa.",
                R.drawable.ic_product_5
            ),
            EquipmentModel(
                9,
                "Microfone",
                "O microfone é um dispositivo de áudio projetado para capturar e amplificar o som de fontes sonoras, como vozes e instrumentos musicais. Composto por um transdutor e um circuito de amplificação, o microfone converte as ondas sonoras em sinais elétricos que podem ser processados e reproduzidos por sistemas de áudio. Amplamente utilizado em ambientes profissionais e de entretenimento, o microfone desempenha um papel crucial na gravação de voz, música e outros conteúdos sonoros, proporcionando uma experiência auditiva de alta qualidade. Disponível em uma variedade de tipos e modelos, como microfones dinâmicos, condensadores e de lapela, o microfone oferece opções versáteis para uma ampla gama de aplicações, como gravação de estúdio, transmissão ao vivo, apresentações e eventos ao vivo.",
                R.drawable.ic_product_4
            )
        )
    }

}