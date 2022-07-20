import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class InputText extends StatelessWidget {
  const InputText({
    Key? key,
    required this.controller,
    required this.labelText,
    this.readOnly = false,
    this.icons,
    this.hintText = "",
    this.textInputType,
    this.inputFormatters
  }) : super(key: key);

  final TextEditingController controller;
  final String labelText, hintText;
  final bool readOnly;
  final Widget? icons;
  final TextInputType? textInputType;
  final List<TextInputFormatter>? inputFormatters;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
            alignment: Alignment.centerLeft,
            margin: const EdgeInsets.only(bottom: 5),
            child: Text(labelText, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold,color: Colors.black54))),
        TextField(
          controller: controller,
          style: const TextStyle(fontSize: 14,color: Colors.black54,fontWeight: FontWeight.w500),
          decoration: InputDecoration(
              suffixIcon: icons,
              hintText: hintText,
              hintStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Colors.black26),
              contentPadding: const EdgeInsets.symmetric(vertical: 0,horizontal: 10),
              border: const OutlineInputBorder(
                borderSide:BorderSide(color: Colors.grey),
                borderRadius: BorderRadius.all(Radius.circular(8)),
              ),
              focusedBorder: const OutlineInputBorder(
                borderSide:BorderSide(color: Colors.purple),
                borderRadius: BorderRadius.all(Radius.circular(8)),
              ),
          ),
          readOnly: readOnly,
          keyboardType: textInputType,
          inputFormatters: inputFormatters,
        )
      ],
    );
  }
}
