WITH data AS (

    -- Colonne: name, type, cfu, year_of_study, mandatory_core, professor_code, degree_name, prefix
    -- mandatory_core = true  → corso obbligatorio, non sostituibile
    -- mandatory_core = false → corso sostituibile

    -- ==========================================
    -- INGEGNERIA GESTIONALE (BACHELOR)
    -- ==========================================
    SELECT 'analisi matematica 1' AS name, 'MATEMATICA' AS type, 6 AS cfu, 1 AS year_of_study, true AS mandatory_course, 'AB12CD34' AS professor_code, 'INGEGNERIA GESTIONALE' AS degree_name, 'MAT' AS prefix
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT'
    UNION ALL SELECT 'metodi di ottimizzazione',                'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT'
    UNION ALL SELECT 'calcolo numerico',                        'MATEMATICA',   6,  1, false, 'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'FIS'
    UNION ALL SELECT 'statistica per l''ingegneria',            'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'INF'
    UNION ALL SELECT 'metodi di rappresentazione tecnica',      'DISEGNO',      6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'LET'
    UNION ALL SELECT 'fisica tecnica e sistemi energetici',     'FISICA',      12,  2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'ricerca operativa',                       'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'MAT's
    UNION ALL SELECT 'gestione aziendale',                      'ECONOMIA',     6,  2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ECO'
    UNION ALL SELECT 'scienza delle costruzioni',               'ING_MECCANICA',6,  2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'meccanica dei fluidi',                    'IDRAULICA',    6,  2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'FIS'
    UNION ALL SELECT 'principi di ingegneria elettrica',        'ING_ELETTRICA',6,  2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'tecnologie meccaniche e dei materiali',   'ING_MECCANICA',12, 2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'impianti industriali',                    'ING_GESTIONALE',9, 3, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'gestione dei progetti',                   'ING_GESTIONALE',9, 3, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'sicurezza degli impianti industriali',    'ING_GESTIONALE',6, 3, false, 'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'progettazione dei processi produttivi',   'ING_GESTIONALE',6, 3, false, 'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'qualità dei processi produttivi',         'ING_GESTIONALE',6, 3, false, 'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'elementi di meccanica delle macchine',    'ING_MECCANICA', 6, 2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_GESTIONALE',6, 3, true,  'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_GESTIONALE',3, 3, true,  'AB12CD34', 'INGEGNERIA GESTIONALE', 'ING'

    -- ==========================================
    -- INGEGNERIA INFORMATICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   12, 1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  9,  1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'programmazione ad oggetti',               'ING_INFORMATICA',9,1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'architettura degli elaboratori',          'ING_INFORMATICA',6,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'sistemi operativi',                       'ING_INFORMATICA',9,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'basi di dati',                            'ING_INFORMATICA',9,2, true,  'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'reti di calcolatori',                     'ING_INFORMATICA',9,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'algoritmi e strutture dati',              'ING_INFORMATICA',9,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'ingegneria del software',                 'ING_INFORMATICA',9,2, true,  'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'fondamenti di elettronica',               'ING_INFORMATICA',6,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'FIS'
    UNION ALL SELECT 'fondamenti di telecomunicazioni',         'ING_INFORMATICA',6,2, true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'sicurezza informatica',                   'ING_INFORMATICA',6,3, false, 'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'intelligenza artificiale',                'ING_INFORMATICA',9,3, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'sviluppo web e mobile',                   'ING_INFORMATICA',6,3, false, 'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'cloud computing',                         'ING_INFORMATICA',6,3, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'sistemi embedded',                        'ING_INFORMATICA',6,3, false, 'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'elaborazione dei segnali',                'ING_INFORMATICA',6,3, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'calcolo numerico',                        'MATEMATICA',   6,  2, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'ricerca operativa',                       'MATEMATICA',   6,  3, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA INFORMATICA', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA INFORMATICA', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA INFORMATICA', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,3,true,   'EF56GH78', 'INGEGNERIA INFORMATICA', 'INF'
    UNION ALL SELECT 'prova finale',                            'ING_INFORMATICA',3,3,true,   'AB12CD34', 'INGEGNERIA INFORMATICA', 'INF'

    -- ==========================================
    -- INGEGNERIA MECCANICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6, 1, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      9,  1, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'INF'
    UNION ALL SELECT 'disegno tecnico industriale',             'DISEGNO',      9,  1, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'scienza delle costruzioni',               'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'meccanica dei fluidi',                    'IDRAULICA',    9,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'FIS'
    UNION ALL SELECT 'fisica tecnica',                          'FISICA',       9,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'FIS'
    UNION ALL SELECT 'costruzione di macchine',                 'ING_MECCANICA',9,  2, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'tecnologie meccaniche e dei materiali',   'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'meccanica applicata alle macchine',       'ING_MECCANICA',9,  2, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'principi di ingegneria elettrica',        'ING_ELETTRICA',6,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'sistemi energetici e macchine a fluido',  'ING_MECCANICA',9,  2, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'impianti meccanici',                      'ING_MECCANICA',6,  3, false, 'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'misure meccaniche e termiche',            'ING_MECCANICA',9,  3, false, 'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'progettazione assistita da calcolatore',  'ING_MECCANICA',6,  3, false, 'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'automazione e controllo',                 'ING_MECCANICA',6,  3, false, 'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'manutenzione industriale',                'ING_MECCANICA',6,  3, false, 'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'metallurgia e scienza dei materiali',     'ING_MECCANICA',9,  3, false, 'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA MECCANICA', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_MECCANICA',6,  3, true,  'EF56GH78', 'INGEGNERIA MECCANICA', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_MECCANICA',3,  3, true,  'AB12CD34', 'INGEGNERIA MECCANICA', 'ING'

    -- ==========================================
    -- INGEGNERIA CIVILE (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6, 1, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'INF'
    UNION ALL SELECT 'disegno tecnico e rappresentazione',      'DISEGNO',      6,  1, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'scienza delle costruzioni',               'ING_MECCANICA',12, 2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'idraulica',                               'IDRAULICA',    9,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'FIS'
    UNION ALL SELECT 'geotecnica',                              'ING_CIVILE',   9,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'topografia e cartografia',                'ING_CIVILE',   6,  2, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'tecnica delle costruzioni',               'ING_CIVILE',   12, 2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'strade e ferrovie',                       'ING_CIVILE',   9,  3, false, 'AB12CD34', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'ponti e strutture',                       'ING_CIVILE',   9,  3, false, 'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'impianti idrici e fognari',               'ING_CIVILE',   6,  3, false, 'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'tecnica urbanistica',                     'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'fisica tecnica',                          'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'FIS'
    UNION ALL SELECT 'geologia applicata',                      'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'meccanica dei fluidi',                    'IDRAULICA',    6,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'FIS'
    UNION ALL SELECT 'calcolo numerico',                        'MATEMATICA',   6,  2, false, 'AB12CD34', 'INGEGNERIA CIVILE', 'MAT'
    UNION ALL SELECT 'sicurezza strutturale',                   'ING_CIVILE',   6,  3, false, 'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'costruzioni in cemento armato',           'ING_CIVILE',   9,  3, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA CIVILE', 'LET'
    UNION ALL SELECT 'economia e gestione dei lavori',          'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA CIVILE', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  3, true,  'EF56GH78', 'INGEGNERIA CIVILE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_CIVILE',   3,  3, true,  'AB12CD34', 'INGEGNERIA CIVILE', 'ING'

    -- ==========================================
    -- INGEGNERIA ELETTRICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6, 1, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      9,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'INF'
    UNION ALL SELECT 'circuiti elettrici 1',                    'ING_ELETTRICA',9,  1, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'circuiti elettrici 2',                    'ING_ELETTRICA',9,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'campi elettromagnetici',                  'ING_ELETTRICA',9,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'macchine elettriche',                     'ING_ELETTRICA',9,  3, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'azionamenti elettrici',                   'ING_ELETTRICA',6,  3, false, 'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'elettronica di potenza',                  'ING_ELETTRICA',9,  3, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'impianti elettrici civili e industriali', 'ING_ELETTRICA',9,  3, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'misure elettriche',                       'ING_ELETTRICA',6,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'sistemi di controllo',                    'ING_ELETTRICA',9,  3, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'trasmissione e distribuzione energia',    'ING_ELETTRICA',6,  3, false, 'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'fondamenti di elettronica',               'ING_ELETTRICA',6,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'MAT'
    UNION ALL SELECT 'calcolo numerico',                        'MATEMATICA',   6,  2, false, 'AB12CD34', 'INGEGNERIA ELETTRICA', 'MAT'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA ELETTRICA', 'ECO'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'LET'
    UNION ALL SELECT 'tirocinio',                               'ING_ELETTRICA',6,  3, true,  'EF56GH78', 'INGEGNERIA ELETTRICA', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_ELETTRICA',3,  3, true,  'AB12CD34', 'INGEGNERIA ELETTRICA', 'ING'

    -- ==========================================
    -- INGEGNERIA ELETTRONICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6, 1, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'INF'
    UNION ALL SELECT 'circuiti elettronici 1',                  'ING_ELETTRONICA',9,1, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'circuiti elettronici 2',                  'ING_ELETTRONICA',9,2, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'dispositivi elettronici',                 'ING_ELETTRONICA',9,2, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'campi elettromagnetici',                  'ING_ELETTRONICA',9,2, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'elettronica digitale',                    'ING_ELETTRONICA',9,2, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'microelettronica',                        'ING_ELETTRONICA',9,3, false, 'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'sistemi di controllo',                    'ING_ELETTRONICA',6,3, false, 'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'elaborazione dei segnali',                'ING_ELETTRONICA',9,3, false, 'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'progettazione di circuiti integrati',     'ING_ELETTRONICA',6,3, false, 'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'sistemi embedded',                        'ING_ELETTRONICA',6,3, false, 'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'misure elettroniche',                     'ING_ELETTRONICA',6,2, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'fondamenti di telecomunicazioni',         'ING_ELETTRONICA',6,3, true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, false, 'AB12CD34', 'INGEGNERIA ELETTRONICA', 'MAT'
    UNION ALL SELECT 'calcolo numerico',                        'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA ELETTRONICA', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA ELETTRONICA', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_ELETTRONICA',6,3,true,   'EF56GH78', 'INGEGNERIA ELETTRONICA', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_ELETTRONICA',3,3,true,   'AB12CD34', 'INGEGNERIA ELETTRONICA', 'ING'

    -- ==========================================
    -- INGEGNERIA AMBIENTALE (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   12, 1, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'CHI'
    UNION ALL SELECT 'chimica organica',                        'CHIMICA',      6,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'INF'
    UNION ALL SELECT 'idraulica',                               'IDRAULICA',    9,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'FIS'
    UNION ALL SELECT 'geologia applicata',                      'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'ecologia e biologia ambientale',          'ALTRO',        6,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'impianti di trattamento acque',           'ING_CIVILE',   9,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'gestione dei rifiuti solidi',             'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'bonifica dei siti contaminati',           'ING_CIVILE',   6,  3, false, 'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'energia da fonti rinnovabili',            'ING_CIVILE',   9,  3, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'inquinamento atmosferico',                'ING_CIVILE',   6,  3, false, 'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'valutazione di impatto ambientale',       'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'fisica tecnica ambientale',               'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'FIS'
    UNION ALL SELECT 'chimica ambientale',                      'CHIMICA',      6,  3, false, 'EF56GH78', 'INGEGNERIA AMBIENTALE', 'CHI'
    UNION ALL SELECT 'statistica ambientale',                   'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'MAT'
    UNION ALL SELECT 'diritto ambientale',                      'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE', 'ECO'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'LET'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  3, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_CIVILE',   3,  3, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE', 'ING'

    -- ==========================================
    -- INGEGNERIA EDILE (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6, 1, true,  'AB12CD34', 'INGEGNERIA EDILE', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA EDILE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA EDILE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA EDILE', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA EDILE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA EDILE', 'INF'
    UNION ALL SELECT 'disegno architettonico e tecnico',        'DISEGNO',      9,  1, true,  'AB12CD34', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'scienza delle costruzioni',               'ING_MECCANICA',12, 2, true,  'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'tecnica delle costruzioni',               'ING_CIVILE',   12, 2, true,  'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'geotecnica',                              'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'fisica tecnica edilizia',                 'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA EDILE', 'FIS'
    UNION ALL SELECT 'impianti tecnici negli edifici',          'ING_CIVILE',   9,  2, true,  'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'topografia',                              'ING_CIVILE',   6,  2, true,  'AB12CD34', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'architettura tecnica',                    'ING_CIVILE',   9,  3, false, 'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'restauro e recupero edilizio',            'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'sicurezza nei cantieri',                  'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'estimo e valutazione immobiliare',        'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA EDILE', 'ECO'
    UNION ALL SELECT 'urbanistica e pianificazione',            'ING_CIVILE',   6,  3, false, 'AB12CD34', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'costruzioni in acciaio',                  'ING_CIVILE',   6,  3, false, 'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'diritto e legislazione edilizia',         'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA EDILE', 'ECO'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA EDILE', 'LET'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  3, true,  'EF56GH78', 'INGEGNERIA EDILE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_CIVILE',   3,  3, true,  'AB12CD34', 'INGEGNERIA EDILE', 'ING'

    -- ==========================================
    -- INGEGNERIA DELL'AUTOMAZIONE (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  9,  1, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'INF'
    UNION ALL SELECT 'circuiti elettrici',                      'ING_ELETTRICA',9,  1, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'elettronica analogica e digitale',        'ING_ELETTRONICA',9,2, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'fondamenti di automatica',                'ING_INFORMATICA',12,2,true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'controllo dei sistemi dinamici',          'ING_INFORMATICA',9,2, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'sistemi di controllo industriale',        'ING_INFORMATICA',9,3, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'robotica industriale',                    'ING_INFORMATICA',9,3, true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'programmazione di plc e sistemi scada',   'ING_INFORMATICA',6,3, false, 'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'visione artificiale',                     'ING_INFORMATICA',6,3, false, 'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'reti di calcolatori industriali',         'ING_INFORMATICA',6,3, false, 'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'azionamenti elettrici',                   'ING_ELETTRICA',6,  3, false, 'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'MAT'
    UNION ALL SELECT 'automazione industriale',                 'ING_GESTIONALE',6, 3, false, 'EF56GH78', 'INGEGNERIA GESTIONALE', 'ING'
    UNION ALL SELECT 'misure e strumentazione',                 'ING_INFORMATICA',6,2,true,   'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,3,true,   'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_INFORMATICA',3,3,true,   'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE', 'ING'

    -- ==========================================
    -- INGEGNERIA DELLE TELECOMUNICAZIONI (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'circuiti elettrici',                      'ING_ELETTRICA',9,  1, true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'ING'
    UNION ALL SELECT 'fondamenti di elettronica',               'ING_ELETTRONICA',9,2,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'ING'
    UNION ALL SELECT 'teoria dei segnali',                      'ING_INFORMATICA',9,2,true,   'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'trasmissione numerica',                   'ING_INFORMATICA',9,2,true,   'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'reti di telecomunicazioni',               'ING_INFORMATICA',9,2,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'propagazione e antenne',                  'ING_INFORMATICA',9,3,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'reti wireless e mobile',                  'ING_INFORMATICA',6,3,false,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'sicurezza delle reti',                    'ING_INFORMATICA',6,3,false,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'sistemi multimediali',                    'ING_INFORMATICA',6,3,false,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'elaborazione dei segnali digitali',       'ING_INFORMATICA',9,3,false,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'campi elettromagnetici',                  'ING_ELETTRICA',9,  2, true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,3,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'
    UNION ALL SELECT 'prova finale',                            'ING_INFORMATICA',3,3,true,   'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'INF'

    -- ==========================================
    -- INGEGNERIA DEI SISTEMI MEDICALI (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'circuiti elettronici biomedici',          'ING_ELETTRONICA',9,2,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ING'
    UNION ALL SELECT 'fisiologia e anatomia per ingegneri',     'ALTRO',        9,  1, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ING'
    UNION ALL SELECT 'biomeccanica',                            'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ING'
    UNION ALL SELECT 'elaborazione di segnali biomedici',       'ING_INFORMATICA',9,2,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'imaging medicale',                        'ING_INFORMATICA',9,3,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'dispositivi medici impiantabili',         'ING_ELETTRONICA',6,3,false,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ING'
    UNION ALL SELECT 'telemedicina e e-health',                 'ING_INFORMATICA',6,3,false,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'intelligenza artificiale in medicina',    'ING_INFORMATICA',6,3,false,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'materiali biocompatibili',                'ING_MECCANICA',6,  3, false, 'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ING'
    UNION ALL SELECT 'normativa e certificazione dispositivi medici','ECONOMIA', 6,  3, false, 'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'ECO'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'LET'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,3,true,   'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'
    UNION ALL SELECT 'prova finale',                            'ING_INFORMATICA',3,3,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI', 'INF'

    -- ==========================================
    -- INGEGNERIA AEROSPAZIALE (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'MAT'
    UNION ALL SELECT 'fisica generale 1',                       'FISICA',       6,  1, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'FIS'
    UNION ALL SELECT 'fisica generale 2',                       'FISICA',       6,  2, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'INF'
    UNION ALL SELECT 'aerodinamica',                            'ING_MECCANICA',12, 2, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'meccanica del volo',                      'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'propulsione aerospaziale',                'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'strutture aerospaziali',                  'ING_MECCANICA',9,  2, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'materiali per l''aerospazio',             'ING_MECCANICA',6,  3, false, 'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'sistemi di guida e navigazione',          'ING_INFORMATICA',9,3,false,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'INF'
    UNION ALL SELECT 'progettazione di velivoli',               'ING_MECCANICA',9,  3, false, 'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'gasdinamica',                             'ING_MECCANICA',9,  3, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'LET'
    UNION ALL SELECT 'economia e gestione dei progetti aerospaziali','ECONOMIA', 6,  3, false, 'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_MECCANICA',6,  3, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE', 'ING'
    UNION ALL SELECT 'prova finale',                            'ING_MECCANICA',3,  3, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE', 'ING'

    -- ==========================================
    -- INGEGNERIA CHIMICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'MAT'
    UNION ALL SELECT 'fisica generale',                         'FISICA',      12,  1, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'FIS'
    UNION ALL SELECT 'chimica generale e inorganica',           'CHIMICA',      9,  1, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'chimica organica',                        'CHIMICA',      9,  1, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'chimica fisica',                          'CHIMICA',      9,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'INF'
    UNION ALL SELECT 'termodinamica chimica',                   'CHIMICA',      9,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'fenomeni di trasporto',                   'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'ING'
    UNION ALL SELECT 'operazioni unitarie 1',                   'CHIMICA',      9,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'operazioni unitarie 2',                   'CHIMICA',      9,  3, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'reattori chimici',                        'CHIMICA',      9,  3, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'impianti chimici',                        'CHIMICA',      9,  3, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'sicurezza degli impianti chimici',        'CHIMICA',      6,  3, false, 'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'statistica e probabilità',                'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'LET'
    UNION ALL SELECT 'economia e organizzazione aziendale',     'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA CHIMICA', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'CHIMICA',      6,  3, true,  'EF56GH78', 'INGEGNERIA CHIMICA', 'CHI'
    UNION ALL SELECT 'prova finale',                            'CHIMICA',      3,  3, true,  'AB12CD34', 'INGEGNERIA CHIMICA', 'CHI'

    -- ==========================================
    -- INGEGNERIA BIOMEDICA (BACHELOR)
    -- ==========================================
    UNION ALL SELECT 'analisi matematica 1',                    'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'MAT'
    UNION ALL SELECT 'analisi matematica 2',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'MAT'
    UNION ALL SELECT 'geometria e algebra lineare',             'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'MAT'
    UNION ALL SELECT 'fisica generale',                         'FISICA',      12,  1, true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'FIS'
    UNION ALL SELECT 'chimica generale',                        'CHIMICA',      6,  1, true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'CHI'
    UNION ALL SELECT 'fondamenti di informatica',               'INFORMATICA',  6,  1, true,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'INF'
    UNION ALL SELECT 'biologia cellulare e molecolare',         'ALTRO',        6,  1, true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'fisiologia umana',                        'ALTRO',        9,  2, true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'biomeccanica',                            'ING_MECCANICA',9,  2, true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'circuiti elettronici biomedici',          'ING_ELETTRONICA',9,2,true,   'AB12CD34', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'elaborazione di segnali biologici',       'ING_INFORMATICA',9,2,true,   'AB12CD34', 'INGEGNERIA BIOMEDICA', 'INF'
    UNION ALL SELECT 'imaging biomedico',                       'ING_INFORMATICA',9,3,true,   'EF56GH78', 'INGEGNERIA BIOMEDICA', 'INF'
    UNION ALL SELECT 'biomateriali',                            'ING_MECCANICA',6,  3, false, 'EF56GH78', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'sistemi di monitoraggio clinico',         'ING_ELETTRONICA',6,3,false,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'ING'
    UNION ALL SELECT 'normativa e sicurezza biomedica',         'ECONOMIA',     6,  3, false, 'AB12CD34', 'INGEGNERIA BIOMEDICA', 'ECO'
    UNION ALL SELECT 'statistica biologica',                    'MATEMATICA',   6,  2, true,  'AB12CD34', 'INGEGNERIA BIOMEDICA', 'MAT'
    UNION ALL SELECT 'inglese tecnico',                         'LINGUA_STRANIERA',3,1,true,  'EF56GH78', 'INGEGNERIA BIOMEDICA', 'LET'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,3,true,   'EF56GH78', 'INGEGNERIA BIOMEDICA', 'INF'
    UNION ALL SELECT 'prova finale',                            'ING_INFORMATICA',3,3,true,   'AB12CD34', 'INGEGNERIA BIOMEDICA', 'INF'

    -- ==========================================
    -- MAGISTRALI — tutte true per corsi fondamentali,
    -- false per corsi specialistici opzionali
    -- ==========================================

    -- INGEGNERIA GESTIONALE MAGISTRALE
    UNION ALL SELECT 'analisi dei sistemi dinamici',            'MATEMATICA',   6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'MAT'
    UNION ALL SELECT 'sistemi informativi aziendali',           'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'supply chain management avanzato',        'ING_GESTIONALE',9, 1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'strategia e innovazione',                 'ECONOMIA',     6,  1, true,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ECO'
    UNION ALL SELECT 'big data analytics',                      'ING_INFORMATICA',6,1,false,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'industria 4.0 e fabbrica digitale',       'ING_GESTIONALE',9, 1, false, 'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'internet of things',                      'ING_INFORMATICA',6,1,false,  'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'fondamenti di cybersecurity',             'ING_INFORMATICA',6,2,false,  'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'basi di dati avanzate',                   'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'economia circolare e sostenibilità',      'ECONOMIA',     6,  2, false, 'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ECO'
    UNION ALL SELECT 'project management avanzato',             'ING_GESTIONALE',9, 2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'finanza aziendale',                       'ECONOMIA',     6,  2, false, 'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ECO'
    UNION ALL SELECT 'logistica industriale',                   'ING_GESTIONALE',6, 2, false, 'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'gestione della produzione industriale',   'ING_GESTIONALE',12,2, true,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_GESTIONALE',6, 2, true,  'EF56GH78', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_GESTIONALE',12,2, true,  'AB12CD34', 'INGEGNERIA GESTIONALE MAGISTRALE', 'ING'

    -- INGEGNERIA INFORMATICA MAGISTRALE
    UNION ALL SELECT 'analisi dei sistemi dinamici',            'MATEMATICA',   6,  1, true,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'MAT'
    UNION ALL SELECT 'sistemi informativi distribuiti',         'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'machine learning e deep learning',        'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'cloud e edge computing',                  'ING_INFORMATICA',6,1,false,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'cybersecurity avanzata',                  'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'internet of things avanzato',             'ING_INFORMATICA',6,1,false,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'basi di dati avanzate',                   'ING_INFORMATICA',6,1,false,  'AB12CD34', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'compilatori e linguaggi',                 'ING_INFORMATICA',9,2,false,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'big data e data engineering',             'ING_INFORMATICA',9,2,false,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'blockchain e sistemi distribuiti',        'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'vision e robotica',                       'ING_INFORMATICA',6,2,false,  'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,2,true,   'EF56GH78', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'tesi magistrale',                         'ING_INFORMATICA',12,2,true,  'AB12CD34', 'INGEGNERIA INFORMATICA MAGISTRALE', 'INF'

    -- INGEGNERIA MECCANICA MAGISTRALE
    UNION ALL SELECT 'analisi dei sistemi dinamici',            'MATEMATICA',   6,  1, true,  'EF56GH78', 'INGEGNERIA MECCANICA MAGISTRALE', 'MAT'
    UNION ALL SELECT 'turbomacchine',                           'ING_MECCANICA',9,  1, true,  'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'gasdinamica e fluidodinamica avanzata',   'ING_MECCANICA',9,  1, true,  'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'FIS'
    UNION ALL SELECT 'progettazione avanzata di macchine',      'ING_MECCANICA',9,  1, true,  'EF56GH78', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'meccatronica',                            'ING_MECCANICA',9,  1, true,  'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'materiali avanzati per l''ingegneria',    'ING_MECCANICA',6,  2, false, 'EF56GH78', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'simulazione numerica e FEM',              'ING_MECCANICA',9,  2, false, 'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'produzione additiva e manifattura avanzata','ING_MECCANICA',6, 2, false, 'EF56GH78', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'manutenzione predittiva e affidabilità',  'ING_MECCANICA',6,  2, false, 'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_MECCANICA',6,  2, true,  'EF56GH78', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_MECCANICA',24, 2, true,  'AB12CD34', 'INGEGNERIA MECCANICA MAGISTRALE', 'ING'

    -- INGEGNERIA ELETTRICA MAGISTRALE
    UNION ALL SELECT 'macchine elettriche avanzate',            'ING_ELETTRICA',9,  1, true,  'AB12CD34', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'azionamenti elettrici avanzati',          'ING_ELETTRICA',9,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'sistemi di potenza e reti elettriche',    'ING_ELETTRICA',9,  1, true,  'EF56GH78', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'energia rinnovabile e smart grid',        'ING_ELETTRICA',9,  1, true,  'AB12CD34', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'elettronica di potenza avanzata',         'ING_ELETTRICA',9,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'impianti elettrici industriali avanzati', 'ING_ELETTRICA',6,  2, false, 'AB12CD34', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'veicoli elettrici e mobilità sostenibile','ING_ELETTRICA',6,  2, false, 'EF56GH78', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'qualità dell''energia e compatibilità elettromagnetica','ING_ELETTRICA',6,2,false,'AB12CD34','INGEGNERIA ELETTRICA MAGISTRALE','ING'
    UNION ALL SELECT 'tirocinio',                               'ING_ELETTRICA',6,  2, true,  'EF56GH78', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_ELETTRICA',24, 2, true,  'AB12CD34', 'INGEGNERIA ELETTRICA MAGISTRALE', 'ING'

    -- INGEGNERIA ELETTRONICA MAGISTRALE
    UNION ALL SELECT 'microelettronica avanzata',               'ING_ELETTRONICA',9,1,true,   'EF56GH78', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'progettazione di sistemi su chip',        'ING_ELETTRONICA',9,1,true,   'AB12CD34', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'elaborazione avanzata dei segnali',       'ING_ELETTRONICA',9,1,true,   'EF56GH78', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'sistemi radar e sonar',                   'ING_ELETTRONICA',6,1,false,  'AB12CD34', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'fotonica e optoelettronica',              'ING_ELETTRONICA',6,2,false,  'EF56GH78', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'intelligenza artificiale per l''elettronica','ING_ELETTRONICA',6,2,false,'AB12CD34','INGEGNERIA ELETTRONICA MAGISTRALE','ING'
    UNION ALL SELECT 'sistemi di comunicazione avanzati',       'ING_ELETTRONICA',9,2,false,  'EF56GH78', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_ELETTRONICA',6,2,true,   'EF56GH78', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_ELETTRONICA',24,2,true,  'AB12CD34', 'INGEGNERIA ELETTRONICA MAGISTRALE', 'ING'

    -- INGEGNERIA CIVILE MAGISTRALE
    UNION ALL SELECT 'strutture in cemento armato avanzate',    'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'dinamica strutturale e ingegneria sismica','ING_CIVILE',  9,  1, true,  'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'geotecnica avanzata',                     'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'ingegneria idraulica avanzata',           'IDRAULICA',    9,  1, true,  'AB12CD34', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'pianificazione dei trasporti',            'ING_CIVILE',   6,  2, false, 'AB12CD34', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'sostenibilità nelle costruzioni',         'ING_CIVILE',   6,  2, false, 'AB12CD34', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'gestione dei rischi ambientali',          'ING_CIVILE',   6,  2, false, 'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'costruzioni in zona sismica',             'ING_CIVILE',   9,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_CIVILE',   24, 2, true,  'AB12CD34', 'INGEGNERIA CIVILE MAGISTRALE', 'ING'

    -- INGEGNERIA AMBIENTALE MAGISTRALE
    UNION ALL SELECT 'gestione integrata delle acque',          'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tecnologie per le energie rinnovabili',   'ING_CIVILE',   9,  1, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'modellazione ambientale',                 'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'cambiamento climatico e adattamento',     'ING_CIVILE',   6,  1, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'economia ambientale',                     'ECONOMIA',     6,  2, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ECO'
    UNION ALL SELECT 'bonifica e ripristino ambientale',        'ING_CIVILE',   9,  2, false, 'EF56GH78', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'gestione del territorio e GIS',           'ING_CIVILE',   6,  2, false, 'AB12CD34', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_CIVILE',   24, 2, true,  'AB12CD34', 'INGEGNERIA AMBIENTALE MAGISTRALE', 'ING'

    -- INGEGNERIA EDILE MAGISTRALE
    UNION ALL SELECT 'progettazione strutturale avanzata',      'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'building information modeling',           'ING_CIVILE',   6,  1, true,  'AB12CD34', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'efficienza energetica degli edifici',     'ING_CIVILE',   9,  1, true,  'EF56GH78', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'restauro e conservazione avanzata',       'ING_CIVILE',   6,  1, false, 'AB12CD34', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'gestione del cantiere e project management','ECONOMIA',   6,  2, false, 'AB12CD34', 'INGEGNERIA EDILE MAGISTRALE', 'ECO'
    UNION ALL SELECT 'acustica edilizia',                       'ING_CIVILE',   6,  2, false, 'EF56GH78', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'normativa antisismica avanzata',          'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'materiali innovativi per l''edilizia',    'ING_CIVILE',   6,  2, false, 'AB12CD34', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_CIVILE',   6,  2, true,  'EF56GH78', 'INGEGNERIA EDILE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_CIVILE',   24, 2, true,  'AB12CD34', 'INGEGNERIA EDILE MAGISTRALE', 'ING'

    -- INGEGNERIA DELL'AUTOMAZIONE MAGISTRALE
    UNION ALL SELECT 'controllo robusto e adattativo',          'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'robotica avanzata',                       'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'sistemi cyber-fisici',                    'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'intelligenza artificiale per l''automazione','ING_INFORMATICA',6,1,false,'EF56GH78','INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE','ING'
    UNION ALL SELECT 'sistemi di automazione industriale avanzati','ING_INFORMATICA',9,2,true,'AB12CD34','INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE','ING'
    UNION ALL SELECT 'reti industriali e industria 4.0',        'ING_INFORMATICA',6,2,false,  'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'veicoli autonomi',                        'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,2,true,   'EF56GH78', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_INFORMATICA',24,2,true,  'AB12CD34', 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'ING'

    -- INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE
    UNION ALL SELECT 'reti 5g e beyond',                        'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'comunicazioni ottiche',                   'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'elaborazione avanzata dei segnali',       'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'sicurezza delle telecomunicazioni',       'ING_INFORMATICA',6,1,false,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'internet of things per le tlc',           'ING_INFORMATICA',6,2,false,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'intelligenza artificiale per le reti',    'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'sistemi satellitari',                     'ING_INFORMATICA',6,2,false,  'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,2,true,   'EF56GH78', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'
    UNION ALL SELECT 'tesi magistrale',                         'ING_INFORMATICA',24,2,true,  'AB12CD34', 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'INF'

    -- INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE
    UNION ALL SELECT 'imaging avanzato e diagnostica',          'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'
    UNION ALL SELECT 'intelligenza artificiale in medicina',    'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'
    UNION ALL SELECT 'robotica chirurgica',                     'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'
    UNION ALL SELECT 'biomateriali avanzati',                   'ING_MECCANICA',6,  1, false, 'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'ING'
    UNION ALL SELECT 'telemedicina avanzata',                   'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'
    UNION ALL SELECT 'stampa 3d biomedica',                     'ING_MECCANICA',6,  2, false, 'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'ING'
    UNION ALL SELECT 'gestione delle tecnologie sanitarie',     'ECONOMIA',     6,  2, false, 'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'ECO'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,2,true,   'EF56GH78', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'
    UNION ALL SELECT 'tesi magistrale',                         'ING_INFORMATICA',24,2,true,  'AB12CD34', 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'INF'

    -- INGEGNERIA AEROSPAZIALE MAGISTRALE
    UNION ALL SELECT 'aerodinamica avanzata',                   'ING_MECCANICA',9,  1, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'propulsione avanzata',                    'ING_MECCANICA',9,  1, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'strutture aerospaziali avanzate',         'ING_MECCANICA',9,  1, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'sistemi spaziali',                        'ING_MECCANICA',9,  1, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'controllo del volo',                      'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'INF'
    UNION ALL SELECT 'materiali compositi avanzati',            'ING_MECCANICA',6,  2, false, 'EF56GH78', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tirocinio',                               'ING_MECCANICA',6,  2, true,  'EF56GH78', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'
    UNION ALL SELECT 'tesi magistrale',                         'ING_MECCANICA',24, 2, true,  'AB12CD34', 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'ING'

    -- INGEGNERIA CHIMICA MAGISTRALE
    UNION ALL SELECT 'ingegneria delle reazioni avanzata',      'CHIMICA',      9,  1, true,  'EF56GH78', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'processi per la sostenibilità',           'CHIMICA',      9,  1, true,  'AB12CD34', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'nanotecnologie e materiali funzionali',   'CHIMICA',      9,  1, false, 'EF56GH78', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'bioingegneria chimica',                   'CHIMICA',      6,  1, false, 'AB12CD34', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'simulazione di processo',                 'CHIMICA',      9,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'ingegneria dei polimeri',                 'CHIMICA',      6,  2, false, 'AB12CD34', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'tirocinio',                               'CHIMICA',      6,  2, true,  'EF56GH78', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'
    UNION ALL SELECT 'tesi magistrale',                         'CHIMICA',      24, 2, true,  'AB12CD34', 'INGEGNERIA CHIMICA MAGISTRALE', 'CHI'

    -- INGEGNERIA BIOMEDICA MAGISTRALE
    UNION ALL SELECT 'bioinformatica avanzata',                 'ING_INFORMATICA',9,1,true,   'AB12CD34', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'neuroscienze computazionali',             'ING_INFORMATICA',9,1,true,   'EF56GH78', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'organoid e tissue engineering',           'ING_MECCANICA',9,  1, false, 'EF56GH78', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'imaging molecolare',                      'ING_INFORMATICA',6,1,false,  'AB12CD34', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'dispositivi impiantabili avanzati',       'ING_ELETTRONICA',9,2,false,  'EF56GH78', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'ING'
    UNION ALL SELECT 'medicina personalizzata e genomica',      'ING_INFORMATICA',6,2,false,  'AB12CD34', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'tirocinio',                               'ING_INFORMATICA',6,2,true,   'EF56GH78', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
    UNION ALL SELECT 'tesi magistrale',                         'ING_INFORMATICA',24,2,true,  'AB12CD34', 'INGEGNERIA BIOMEDICA MAGISTRALE', 'INF'
)

INSERT INTO academic.courses (
    id,
    miur_course_code,
    name,
    type,
    cfu,
    year_of_study,
    mandatory_course,
    professor_id,
    degree_course_id
)
SELECT
    gen_random_uuid(),
    d.prefix || '-' || gen_random_uuid(),
    d.name,
    d.type,
    d.cfu,
    d.year_of_study,
    d.mandatory_course,
    p.id,
    dc.id
FROM data d
JOIN people.professors p
    ON p.professor_code = d.professor_code
JOIN academic.degree_courses dc
    ON dc.name = d.degree_name
WHERE NOT EXISTS (
    SELECT 1 FROM academic.courses c
    JOIN academic.degree_courses dc2 ON c.degree_course_id = dc2.id
    WHERE c.name = d.name
    AND dc2.name = d.degree_name
);