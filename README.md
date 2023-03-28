![sdk_5.6 badge](https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/sdk_5.6.json)
![arch_7.16 badge](https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/arch_7.16.json)
![gui_3 badge](https://shields.microej.com/endpoint?url=https://repository.microej.com/packages/badges/gui_3.json)

# Overview

This project show cases a simple use of the MicroEJ Zxing library to encode and display QRCode at runtime.

# <a name="requirements"></a>Requirements

- MicroEJ SDK 5.6.0 or higher.
- A MicroEJ VEE Port with at least:
  - EDC-1.3.6
  - MicroUI-3.1.1
  - BON-1.4.0


# Usage

First, import the project into the MicroEJ SDK:
- In MicroEJ SDK, go to ``File`` -> ``Import...``
- In the ``General`` section, select ``Existing Projects into Workspace`` , click ``Next >``
- Select ``Select root directory`` then browse for the project root folder
- In the ``Projects:`` list, make sure that the Eclipse project is selected, click ``Finish``

This project comes with a premade launcher to avoid properties manual configuration.  
By default, the launcher uses the [STM32F7508 1.5.0 VEE Port](https://github.com/MicroEJ/VEEPort-STMicroelectronics-STM32F7508-DK/tree/1.5.0) please refer to the GitHub repository README to import and build the VEE Port.

You can now run the application:
- In MicroEJ SDK, go to ``Run`` -> ``Run Configurations...``
- Expand the MicroEJ Application section, click on the existing ``ZXing example Main`` launcher
- If the launcher can't find the VEE Port stated above, you need to manually provide a VEE Port fulfilling the criterias given in the [Requirements section](#requirements).
- Click ``Run``, the application will start on the Simulator.

Once the application is running, you can click on one of the 3 bottom buttons to generate different QRCodes with colors and logos.

# Dependencies

_All dependencies are retrieved transitively by MicroEJ Module Manager_.

# Source

N/A.

# Troubleshooting

If you are encountering ``java.lang.OutOfMemoryError`` error when launching the application in simulation this is mostly due to the fact that you are not using the properties set in the launcher, make sure that you are using the provided launcher (run configuration).  
If you specifically want to use an other launcher, you can fix the error by following these steps:
- In MicroEJ SDK, click ``Run`` -> ``Run Configurations...``, select the run configuration of the project
- Go into the ``Configuration`` tab
- In the ``Runtime`` section, open the ``Memory`` tab
- Increase the ``Java heap size (in bytes)`` to 128 000

This should fix the issue ``java.lang.OutOfMemoryError``.

# Restrictions

None.

---

_Copyright 2023 MicroEJ Corp. All rights reserved._  
_Use of this source code is governed by a BSD-style license that can be found with this software._

