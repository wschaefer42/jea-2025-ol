<#macro header title>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>${title!'Title'}</title>
    </head>
    <style>
        body {
            background-color: white;
        }
        table, td, th {
            padding: 2px 5px;
            border: 1px solid;
            text-align: left;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
    </style>
    <body style="text-align: left; font-family: sans-serif">
    <#if title??>
        <h2>${title?cap_first}</h2>
    </#if>
    <#nested>
    </body>
    </html>
</#macro>