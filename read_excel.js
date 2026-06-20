const XLSX = require('xlsx');
const path = require('path');
const fs = require('fs');

const filePath = path.join(__dirname, 'New Tender -  (1).xlsx');
const workbook = XLSX.readFile(filePath);

let output = '';
workbook.SheetNames.forEach(sheetName => {
  output += `\n=== Sheet: ${sheetName} ===\n`;
  const sheet = workbook.Sheets[sheetName];
  const data = XLSX.utils.sheet_to_json(sheet, { header: 1 });
  data.forEach((row, i) => {
    output += `Row ${i}: ${JSON.stringify(row)}\n`;
  });
  output += `\nTotal rows: ${data.length}\n`;
  
  // Also output as JSON
  const jsonData = XLSX.utils.sheet_to_json(sheet);
  output += `\nJSON Data (first 5 rows):\n${JSON.stringify(jsonData.slice(0, 5), null, 2)}\n`;
  output += `\nHeaders: ${JSON.stringify(data[0])}\n`;
  output += `\nTotal data rows (excluding header): ${jsonData.length}\n`;
});

fs.writeFileSync('tender_data_output.txt', output);
console.log('Output written to tender_data_output.txt');
